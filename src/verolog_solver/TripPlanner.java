/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Apache
 */
public class TripPlanner {
    
    /*public Solution planTrips()
    {
        Solution solution = new Solution();
        FileData fileData = FileData.getInstance();
        ArrayList<Location> locations = fileData.getLocations();
        ArrayList<Request> requestList = fileData.getRequests();
        ArrayList<Request> orderedList = Request.orderByFirstDay(requestList);
        Iterator<Request> it = orderedList.iterator();
        
        while (it.hasNext())
        {
            
            Request request = it.next();
            
            int reqID = request.getRequestID();
            int custID = request.getCustomerID();
            int firstDay = request.getFirstDay();
            int lastDay = request.getLastDay();
            int nbDays = request.getNbDays();
            int toolID = request.getToolID();
            int nbTools = request.getNbTools();
            
            Location nextStop = Location.getByID(custID, locations);
            Trip dropOffTrip = new Trip(firstDay);
            dropOffTrip.addStop(nextStop);
            dropOffTrip.finalize();
            solution.addTrip(dropOffTrip);
            
            
            Trip pickUpTrip = new Trip(firstDay+nbDays);
            pickUpTrip.addStop(nextStop);
            pickUpTrip.finalize();
            solution.addTrip(pickUpTrip);
            
            
        }
        
        return solution;
    }*/
    
    public Solution answerASAP(ArrayList<Request> rqList){
        Solution solution = new Solution();
        ArrayList<Request> requestList = Request.orderByFirstDay(rqList);
        //Iterator<Request> it = requestList.iterator();
        while ( !requestList.isEmpty() )
        //for (int rqIdx = 0 ; rqIdx < requestList.size() ; rqIdx++)
        {
            //Request req = requestList.get(rqIdx);
            Request req = requestList.get(0);
            int firstDay = req.getFirstDay();
            int nbDays = req.getNbDays();
            //if (solution.)
            Trip trip = new Trip(firstDay);
            trip.addStop(req, true);
            trip.goToDepot();
            solution.addTrip(trip);
            trip = new Trip(firstDay+nbDays);
            trip.addStop(req, false);
            trip.goToDepot();
            solution.addTrip(trip);
            requestList.remove(req);
            
        }
        
        return solution;
    }
}
