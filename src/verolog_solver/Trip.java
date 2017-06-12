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
   
    public Trip(int date)
    {
        cost = 0;
        distance = 0;
        this.date = date;
        stops = new LinkedList<>();
    }

    
    
    public void addStop(Location location)
    {
        FileData fileData = FileData.getInstance();
        if( stops.isEmpty() )
        {
            cost += fileData.getVehiculeDayCost();
            cost += location.distanceTo(fileData.getLocations().get(0)) 
                    * fileData.getDistanceCost();
            distance += location.distanceTo(fileData.getLocations().get(0));
            
        }
        else
        {
            cost += location.distanceTo(stops.getLast()) 
                    * fileData.getDistanceCost();
            distance += location.distanceTo(stops.getLast());
        }
        
        stops.add(location);
    }
    
    public void finalize()
    {
        Depot depot = (Depot)FileData.getInstance().getLocations().get(0);
        addStop(depot);
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
