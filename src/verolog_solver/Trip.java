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
    private ArrayList<int[]> loadedTools;
    //int returnTripNb;
    
    public Trip(int date)
    {
        cost = 0;
        distance = 0;
        //returnTripNb = 0;
        this.date = date;
        stops = new LinkedList<>();
        answeredRequests = new LinkedList<>();
        depot = FileData.getInstance().getLocations().get(0);
        fileData = FileData.getInstance();
        cost += fileData.getVehiculeDayCost();
        tripSense = new ArrayList();
        
        //loadedTools  = new int[fileData.getNbTools()];
        loadedTools  = new ArrayList();
        
        goToDepot();
        
    }

    
    
    public void addStop(Request request, boolean delivery)
    {
        Location location = Location.getByID(request.getCustomerID(), fileData.getLocations());
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
        
        
        
        if ( request.getRequestID() > 0 && delivery)
        {
            int toolID = request.getToolID() - 1 ;
            int nbTools = request.getNbTools();
            int [] toolLoad = loadedTools.get( loadedTools.size()-1);
            toolLoad[toolID] += -tripSense.get(tripSense.size()-1) * nbTools;
            //loadedTools[toolID - 1] = -tripSense.get(tripSense.size()-1) * 
            //                                 request.getNbTools();
        }
        
        /*ToolType tool = ToolType.getByID(request.getToolID(), fileData.getToolList());
        if ( tool != null)
        {
            tool.addTools(- request.getNbTools());
        }*/
        stops.add(location);
        answeredRequests.add(request);
    }
    
    public void goToDepot()
    {
        
        addStop(Request.getEmptyRequest(), true);
        //returnTripNb++;
        int[] toolLoad = new int[fileData.getNbTools()];
        
        for (int idx = 0 ; idx < toolLoad.length ; idx ++)
        {
            //fileData.getToolList().get(idx).addTools(loadedTools[idx]);
            toolLoad[idx] = 0;
        }
        for ( int idx = stops.lastIndexOf(depot)-1 ; idx >=0 ; idx -- )
        {
            if ( stops.get(idx).getID() == depot.getID() )
            {
                break;
            }
            else if (tripSense.get(idx) < 0)
            {
                int toolID = answeredRequests.get(idx).getToolID() - 1;
                int nbTools = answeredRequests.get(idx).getNbTools();
                toolLoad[toolID] += - tripSense.get(idx) * nbTools;
                
            }
            
            
        }
        loadedTools.add(toolLoad);
        
        
    }
    
    public int[] getToolLoadAtIndex(int index)
    {
        return loadedTools.get(index);
    }
    
    public int[] getInitialToolLoad()
    {
        return loadedTools.get(0);
    }
    
    public int [] getTotalReturnedTools()
    {
        int[] totalToolLoad = new int[fileData.getNbTools()];
        for (int i = 0 ; i < totalToolLoad.length ; i ++)
        {
            totalToolLoad[i] = 0;
        }
        Iterator<int[]> it = loadedTools.iterator();
        while (it.hasNext())
        {
            int[] array = it.next();
        
            for (int i = 0; i < totalToolLoad.length ; i++)
            {
                //if (array[i] > 0)
                //{
                    totalToolLoad[i] += array[i];
                //}
            }
        }
        return totalToolLoad;
    }
    
    public static ArrayList<Trip> orderByDate(ArrayList<Trip> tripList)
    {
        ArrayList<Trip> orderedList = new ArrayList<>();
        ArrayList<Trip> messyList = new ArrayList(tripList);
        while (!messyList.isEmpty())
        {
            int minDayIndex = 0;
            for (int i = 0 ; i < messyList.size() ; i++)
            {   
                int currentFirstDay = messyList.get(i).getDate();
                int minFirstDay = messyList.get(minDayIndex).getDate();
                
                if ( currentFirstDay < minFirstDay )
                {
                    minDayIndex = i;
                }
            }    
            orderedList.add( messyList.remove(minDayIndex) );
        }
        return orderedList;
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
    public int getNbReturnTrip()
    {
        return loadedTools.size();
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
            //int returnTripNb = 0;
            for ( int index = 0; index < loadedTools.size() ; index ++)
            {
                str += "V\t";
                str += (index + 1) + "\t";
                int[] lastLoadedTools = loadedTools.get(index);
                for (int i = 0; i < lastLoadedTools.length ; i++)
                {
                    str += lastLoadedTools[i] + "\t";
                }
                str += "\n";
            }
            /*Iterator<Location> it = stops.iterator();
            for(int stopIdx = 0 ; stopIdx < stops.size() ; stopIdx ++)//browsing the stops to find a depot
            {
                Location stop = stops.get(stopIdx);
                if ( stop.getID() == depot.getID() )
                {
                    returnTripNb++;
                    //int depotIdx = stops.//.indexOf(stop);
                    //int index = stopIdx;
                    //boolean previousDepotFound = false;
                    int[] lastLoadedTools = new int[fileData.getNbTools()];
                    for (int i = 0 ; i < lastLoadedTools.length ; i++)
                    {
                        lastLoadedTools[i] = 0;
                    }
                    for ( int index = stopIdx + 1 ; index < stops.size() ; index++)
                    {
                        if ( stops.get(index).getID() == depot.getID() &&
                                tripSense.get(index) > 0)
                        {
                            for (int reqIdx = stopIdx+1 ; reqIdx < index ; reqIdx ++)//browsing from the previous stop at depot to current stop
                            {
                                if (tripSense.get(reqIdx) > 0)
                                {
                                    int toolID = answeredRequests.get(reqIdx).getToolID() - 1;
                                    int nbTools = - tripSense.get(reqIdx) * answeredRequests.get(reqIdx).getNbTools();

                                    lastLoadedTools[toolID] += nbTools;
                                }

                            }
                            break;
                        }
                    }
                    for ( int index = stopIdx - 1 ; index >= 0 ; index--)
                    {
                        if ( stops.get(index).getID() == depot.getID() )
                        {
                            for (int reqIdx = index+1 ; reqIdx < stopIdx ; reqIdx ++)//browsing from the previous stop at depot to current stop
                            {
                                if (tripSense.get(reqIdx) < 0)
                                {
                                    int toolID = answeredRequests.get(reqIdx).getToolID() - 1;
                                    int nbTools = - tripSense.get(reqIdx) * answeredRequests.get(reqIdx).getNbTools();

                                    lastLoadedTools[toolID] += nbTools;
                                }

                            }
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
            }*/
            str += "D\t" + this.distance;
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

    public void setDate(int date) {
        this.date = date;
    }
    
    
}
