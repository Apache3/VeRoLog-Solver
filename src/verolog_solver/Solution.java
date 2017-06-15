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
public class Solution {

    private ArrayList<Trip> trips;
    private int totalDistance;
    private int totalCost;

    public Solution()
    {
        trips = new ArrayList<>();
        totalDistance = 0;
        totalCost = 0;
    }

    public void addTrip(Trip trip)
    {
        trips.add(trip);
        totalDistance += trip.getDistance();
        totalCost += trip.getCost();
        trips = Trip.orderByDate(trips);
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
        //LinkedList<Trip> searchList = new LinkedList<>(trips);
        //order searchList by date somewhere
        str += "DATASET = " + FileData.getInstance().getDataset() + "\n";
        str += "NAME = " +FileData.getInstance().getName() + "\n";


        Iterator<Trip> it = trips.iterator();
        int day = 1;
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
            if ( trip.getDate() == day)
            {
                
                
                
            }
            if (trip.getDate() > day)
            {
                vehiculeNbr = 1;
                header = "\nDAY = " + day +"\n";
                header += "NUMBER_OF_VEHICULES = " + getNumberVehiculeByDay(day)+ "\n";
                if ( verbose )
                {
                    String startDepotStr = "";
                    String finishDepotStr = "";
                    for (int i = 0 ; i < startDepot.length ; i++)
                    {
                        startDepotStr += startDepot[i] + "\t";
                        finishDepotStr += finishDepot[i] +"\t";
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
            
            for ( int i = 0 ; i< startDepot.length ; i++)
            {
                startDepot[i] += trip.getInitialToolLoad()[i];
            }
            for ( int i = 0 ; i< finishDepot.length ; i++)
            {
                //finishDepot[i] = startDepot[i];
                finishDepot[i] +=  trip.getTotalReturnedTools()[i];
            }
            tripStr = (vehiculeNbr + "\t").concat(tripStr);
            if (verbose)
            {
                tripStr = tripStr.replaceAll("\n", "\n" + vehiculeNbr + "\t");
            }
            
            allTripStr += tripStr + "\n";
            if (!it.hasNext())
            {
                vehiculeNbr = 1;
                header = "\nDAY = " + day +"\n";
                header += "NUMBER_OF_VEHICULES = " + getNumberVehiculeByDay(day)+ "\n";
                if ( verbose )
                {
                    String startDepotStr = "";
                    String finishDepotStr = "";
                    for (int i = 0 ; i < startDepot.length ; i++)
                    {
                        startDepotStr += startDepot[i] + "\t";
                        finishDepotStr += finishDepot[i] +"\t";
                    }
                    header += "START_DEPOT = " + startDepotStr + "\n";
                    header += "FINISH_DEPOT = " + finishDepotStr +"\n";
                    
                    
                    
                    
                }
                day = trip.getDate();
                str += header + allTripStr + "\n";
                allTripStr = "";
            }
            vehiculeNbr++;
            
            
        }
        return str;
    }
}
