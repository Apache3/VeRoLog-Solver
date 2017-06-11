/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.util.LinkedList;


/**
 *
 * @author Apache
 */
public class Trip {
    
    private int date; // date of the trip
    private int distance; //total distance of the trip
    private int cost; // total cost of the trip
    private LinkedList<Location> stops; //list of the trip's stops.
    private Depot depot;
    
    
    public Trip(int date,Depot depot)
    {
        cost = 0;
        distance = 0;
        this.date = date;
        stops = new LinkedList<>();
        this.depot = depot;
    }

    
    
    public void addStop(Location location)
    {
        if( stops.isEmpty() )
        {
            cost += FileData.getInstance().getVehiculeDayCost();
            cost += location.distanceTo(depot) 
                    * FileData.getInstance().getDistanceCost();
            distance += location.distanceTo(depot);
            
        }
        else
        {
            cost += location.distanceTo(stops.getLast()) 
                    * FileData.getInstance().getDistanceCost();
            distance += location.distanceTo(stops.getLast());
        }
        
        stops.add(location);
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
}
