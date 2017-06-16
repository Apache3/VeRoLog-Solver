/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Apache
 */
public class Solution {

    private ArrayList<Trip> trips;
    private int totalDistance;
    private int totalCost;
    private Map<Integer, int[]> startDepotState;
    private Map<Integer, int[]> finishDepotState;
    private int lastTripDate;

    public Solution()
    {
        trips = new ArrayList<>();
        startDepotState = new HashMap();
        finishDepotState = new HashMap();
        totalDistance = 0;
        totalCost = 0;
        lastTripDate = 0;
        
        int[] initDepot = new int[FileData.getInstance().getNbTools()];//previous finish + startload of all trips
        for ( int i = 0 ; i < initDepot.length ; i++)
        {

            initDepot[i] = FileData.getInstance().getToolList().get(i).getRemainingTools();
        }
        startDepotState.put(0, initDepot);//.add(startDepot);
        finishDepotState.put(0, initDepot.clone());
        
    }
    
    private void updateDepotState()
    {
        trips = Trip.orderByDate(trips);
        startDepotState = new HashMap();
        finishDepotState = new HashMap();
        int[] initDepot = new int[FileData.getInstance().getNbTools()];//previous finish + startload of all trips
        for ( int i = 0 ; i < initDepot.length ; i++)
        {

            initDepot[i] = FileData.getInstance().getToolList().get(i).getRemainingTools();
        }
        startDepotState.put(trips.get(0).getDate(), initDepot);//.add(startDepot);
        finishDepotState.put(trips.get(0).getDate(), initDepot.clone());
        
        Iterator<Trip> it = trips.iterator();
        while ( it.hasNext() )
        {
            Trip trip = it.next();
            int tripDate = trip.getDate();
            int[] startDepot = trip.getInitialToolLoad();
            int[] finishDepot = trip.getTotalReturnedTools();
            
            if ( startDepotState.containsKey(tripDate) )
            {
                for (int i = 0 ; i < startDepotState.get(trip.getDate()).length ; i++)
                {
                    startDepotState.get(trip.getDate())[i] +=startDepot[i];
                    finishDepotState.get(trip.getDate())[i] +=finishDepot[i];

                }
            }
            else
            {
                int dateIdx = tripDate - 1;
                while (!startDepotState.containsKey(dateIdx) && dateIdx > -1 )
                {
                    dateIdx--;
                }

                int[] newStartState = finishDepotState.get(dateIdx).clone();
                int[] newFinishState = newStartState.clone();
                for (int i = 0 ; i < newStartState.length ; i++)
                {
                    newStartState[i] +=startDepot[i];
                    newFinishState[i] +=finishDepot[i];

                }
                startDepotState.put(trip.getDate(), newStartState);
                finishDepotState.put(trip.getDate(), newFinishState);

            }
            
        }
    }
    
    public int getNbToolAvailable(int ID, int date)
    {
        int availableTools = 0;
        if ( startDepotState.containsKey(date) )
        {
            availableTools = startDepotState.get(date)[ID - 1];
        }
        else
        {
            int dateIdx = date-1;
            while (!startDepotState.containsKey(dateIdx))
            {
                dateIdx--;
            }
            availableTools = finishDepotState.get(dateIdx)[ID-1];
        }
        return availableTools;
    }

    public void addTrip(Trip trip)
    {
        
        
        //int[] startDepot = trip.getInitialToolLoad();
        //int[] finishDepot = trip.getTotalReturnedTools();
        Trip addedTrip = trip;
        /*if ( getNumberVehiculeByDay(trip.getDate())>0 )
        {
            addedTrip = this.getLastTripOnDate(trip.getDate());
            
            
            
        }*/
        
        trips.add(trip);
        
        
        
        updateDepotState();
        totalDistance += trip.getDistance();
        totalCost += trip.getCost();
        
        
        
    }

    public boolean equals(Solution solution)
    {
        boolean equivalent;

        equivalent = (solution.getTotalCost() == totalCost)
                  && (solution.getTotalDistance() == totalDistance)
                  && (solution.getTrips().size() == trips.size());

        return equivalent;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalCost() {
        return totalCost;
    }
    
    private Trip getLastTripOnDate(int date)
    {
        Iterator<Trip> it = trips.iterator();
        Trip lastTrip =null;
        while (it.hasNext())
        {
            Trip trip =it.next();
            if(trip.getDate() == date)
            {
                lastTrip = trip;
            }
        }
        return lastTrip;
    }
    
    private int getNumberVehiculeByDay(int day)
    {
        int nbVehicule= 0;
        Iterator<Trip> it = trips.iterator();
        while (it.hasNext())
        {
            Trip trip = it.next();
            if (trip.getDate() == day)
            {
                nbVehicule++;
            }
        }
        return nbVehicule;
    }

    public String toString()
    {
        return toString(false);
    }

    public String toString(boolean verbose)
    {
        String str = "";
        
        str += "DATASET = " + FileData.getInstance().getDataset() + "\n";
        str += "NAME = " +FileData.getInstance().getName() + "\n";

        Iterator<Trip> it = trips.iterator();
        int day = 0;
        int vehiculeNbr = 1;
        int[] startDepot = new int[FileData.getInstance().getNbTools()];//previous finish + startload of all trips
        int[] finishDepot = new int[FileData.getInstance().getNbTools()];//start + total returned tools
        for ( int i = 0 ; i < startDepot.length ; i++)
        {
           
            finishDepot[i] = FileData.getInstance().getToolList().get(i).getRemainingTools();
            startDepot[i] = finishDepot[i];
        }
        
        String header = "";
        String allTripStr = "";
        while (it.hasNext())
        {
            Trip trip = it.next();
            String tripStr = trip.toString(verbose);
            if (trip.getDate() > day)
            {
                vehiculeNbr = 1;
                
                header = "\nDAY = " + trip.getDate() +"\n";
                header += "NUMBER_OF_VEHICULES = " + getNumberVehiculeByDay(trip.getDate())+ "\n";
                if ( verbose )
                {
                    String startDepotStr = "";
                    String finishDepotStr = "";
                    for (int i = 0 ; i < startDepot.length ; i++)
                    {
                        startDepotStr += startDepotState.get(trip.getDate())[i] + "\t";
                        finishDepotStr += finishDepotState.get(trip.getDate())[i] +"\t";
                    }
                    
                    header += "START_DEPOT = " + startDepotStr + "\n";
                    header += "FINISH_DEPOT = " + finishDepotStr +"\n";
                    for (int i = 0 ; i < startDepot.length ; i++)
                    {
                        startDepot[i] = finishDepot[i];
                        
                    }
                }
                
                day = trip.getDate();
                str += header + allTripStr + "\n";
                allTripStr = "";
            }
            tripStr = (vehiculeNbr + "\t").concat(tripStr);
            if (verbose)
            {
                tripStr = tripStr.replaceAll("\n", "\n" + vehiculeNbr + "\t");
            }
            vehiculeNbr++;
            str += tripStr + "\n";
            
        }
        return str;
    }
}
