/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 *
 * @author Apache
 */
public class Solution {
    
    private LinkedList<Trip> trips;
    private int totalDistance;
    private int totalCost;
    
    public Solution()
    {
        trips = new LinkedList<>();
        totalDistance = 0;
        totalCost = 0;
    }
    
    public void addTrip(Trip trip)
    {
        trips.add(trip);
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

    public LinkedList<Trip> getTrips() {
        return trips;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
