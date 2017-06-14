/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author Apache
 */
public class Trip {
    
    private int date; // date of the trip
    private int distance; //total distance of the trip
    private int cost; // total cost of the trip
    private LinkedList<Request> answeredRequests;
    private LinkedList<Location> stops; //list of the trip's stops.
    private Location depot;
    private FileData fileData;
    private ArrayList<Integer> tripSense;
    //private int[] loadedTools;
    
    public Trip(int date)
    {
        cost = 0;
        distance = 0;
        this.date = date;
        stops = new LinkedList<>();
        answeredRequests = new LinkedList<>();
        depot = FileData.getInstance().getLocations().get(0);
        fileData = FileData.getInstance();
        cost += fileData.getVehiculeDayCost();
        tripSense = new ArrayList<>();
        
        /*loadedTools  = new int[fileData.getNbTools()];
        for (int idx = 0 ; idx < fileData.getNbTools() ; idx++)
        {
            loadedTools[idx] = 0;
        }*/
        goToDepot();
        
    }

    
    
    public void addStop(Location location, Request request, boolean delivery)
    {
        if( !stops.isEmpty() )
        {
            cost += location.distanceTo(stops.getLast()) 
                    * fileData.getDistanceCost();

            distance += location.distanceTo(stops.getLast());
        }
        
        if ( delivery )
        {
            tripSense.add(1);
        }
        else
        {
            tripSense.add(-1);
        }
        stops.add(location);
        
        /*loadedTools[request.getToolID()-1] = -tripSense.get(tripSense.size()-1) * 
                                             request.getNbTools();*/
        ToolType tool = ToolType.getByID(request.getToolID(), fileData.getToolList());
        if ( tool != null)
        {
            tool.addTools(- request.getNbTools());
        }
        answeredRequests.add(request);
    }
    
    public void goToDepot()
    {
        
        addStop(depot, Request.getEmptyRequest(), true);
        
        /*for (int idx = 0 ; idx < loadedTools.length ; idx ++)
        {
            fileData.getToolList().get(idx).addTools(loadedTools[idx]);
            loadedTools[idx] = 0;
        }*/
        
        
    }
    
    public int getDate() {
        return date;
    }

    public int getDistance() {
        return distance;
    }

    public int getCost() {
        return cost;
    }

    public LinkedList<Location> getStops() {
        return stops;
    }
    
    public String toString()
    {
        return this.toString(false);
    }
    
    public String toString(boolean verbose)
    {
        String str  = "R\t";
               //str += fileData.getDepotCoordinate() + "\t";
        for ( int i = 0; i< answeredRequests.size() ; i++ )
        {
            Request request = answeredRequests.get(i);
            str += request.getRequestID() * tripSense.get(i) + "\t";
            
        }
        if (verbose)
        {
            
            str += "\n";
            int returnTripNb = 0;
            Iterator<Location> it = stops.iterator();
            while (it.hasNext())//browsing the stops to find a depot
            {
                Location stop = it.next();
                if ( stop.getID() == depot.getID() )
                {
                    
                    int depotIdx = stops.indexOf(stop);
                    int index = depotIdx;
                    boolean previousDepotFound = false;
                    int[] lastLoadedTools = new int[fileData.getNbTools()];
                    for (int i = 0 ; i < lastLoadedTools.length ; i++)
                    {
                        lastLoadedTools[i] = 0;
                    }
                    while (!previousDepotFound)//browsing backwards from the depot index to find other stop at depot
                    {
                        if (index > 0)
                        {
                            returnTripNb++;
                            index--;
                            if ( stops.get(index).getID() == depot.getID() )
                            {
                                previousDepotFound = true;
                                for (int reqIdx = index+1 ; index < depotIdx ; reqIdx ++)//browsing from the previous stop at depot to current stop
                                {
                                    
                                    int toolID = answeredRequests.get(reqIdx).getToolID();
                                    int nbTools = - tripSense.get(reqIdx) * answeredRequests.get(reqIdx).getNbTools();
                                    lastLoadedTools[toolID] += nbTools;
                                    
                                }
                                
                            }
                        }
                        else
                        {
                            break;
                        }
                        
                        
                    }
                    str += "V\t";
                    str += returnTripNb + "\t";
                    for (int i = 0; i < lastLoadedTools.length ; i++)
                    {
                        str += lastLoadedTools[i] + "\t";
                    }
                    str += "\n";
                }
            }
            str += "D\t" + this.distance + "\n";
            /*for (int j = 0 ; j < loadedTools.length ; j++)
            {
                if (loadedTools[j] < 0)
                {
                    str += loadedTools[j] + "\t";
                }
                else
                {
                   str += "0\t"; 
                }
            }
            str += "\nV\t";

            for (int j = 0 ; j < loadedTools.length ; j++)
            {
                if (loadedTools[j] > 0)
                {
                    str += loadedTools[j] + "\t";
                }
                else
                {
                   str += "0\t"; 
                }
            }
            
            str += "\nD\t";
           */ 
        }
        return str;
    }
    
    
}
