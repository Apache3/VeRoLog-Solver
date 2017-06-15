/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Apache
 */
public class VeRoLog_Solver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*System.out.println("args: ");
        System.out.println(args[0]);
        System.out.println();*/
        FileParser fileParser = new FileParser("Data/ORTEC_Test_03.txt");
        fileParser.parse();
        FileData fileData = FileData.getInstance();
        System.out.println(fileData.toString(false));
        ArrayList<Location> locations = fileData.getLocations();
        ArrayList<Request> rqList = Request.orderByFirstDay(fileData.getRequests());
        
        /*for (int i = 0; i< rqlist.size() ; i++)
        {
            System.out.println(rqlist.get(i).toString());
        }*/
        /*try
        {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
        Trip trip = new Trip(1);
        trip.addStop(rqList.get(0), true);
        trip.addStop(rqList.get(1), true);
        trip.addStop(rqList.get(2), true);
        trip.goToDepot();
        trip.addStop( rqList.get(0), false);
        trip.addStop(rqList.get(1), false);
        
        trip.goToDepot();
        trip.addStop(rqList.get(5), true);
        trip.addStop(rqList.get(3), true);
        trip.addStop(rqList.get(4), true);
        trip.addStop(rqList.get(2), false);
        trip.goToDepot();
        trip.addStop(rqList.get(5), false);
        trip.addStop(rqList.get(3), false);
        trip.goToDepot();
        trip.addStop(rqList.get(4), false);
        trip.goToDepot();
        
        Trip trip2 = new Trip(2);
        trip2.addStop(rqList.get(0), true);
        trip2.addStop(rqList.get(1), true);
        trip2.addStop(rqList.get(2), true);
        trip2.goToDepot();
        trip2.addStop( rqList.get(0), false);
        trip2.addStop(rqList.get(1), false);
        
        trip2.goToDepot();
        trip2.addStop(rqList.get(5), true);
        trip2.addStop(rqList.get(3), true);
        trip2.addStop(rqList.get(4), true);
        trip2.addStop(rqList.get(2), false);
        trip2.goToDepot();
        trip2.addStop(rqList.get(5), false);
        trip2.addStop(rqList.get(3), false);
        trip2.goToDepot();
        trip2.addStop(rqList.get(4), false);
        trip2.goToDepot();
        
        TripPlanner tripPlanner = new TripPlanner();
        Solution sol = tripPlanner.answerASAP(rqList);
        //Solution sol = new Solution();
        //sol.addTrip(trip);
        //sol.addTrip(trip2);
        
        
        System.out.println("\n--------PRINT OF A SOLUTION--------\n");
        System.out.println(sol.toString(true));
       //FileData fileData = fileParser.getFileData();
        //System.out.println(fileData.toString(false));
        //ArrayList<Location> locations = fileData.getLocations();
        //int dist = locations.get(6).distanceTo(locations.get(0));
        //System.out.println("distance 0 -> 1: " + dist);
        //int cost = fileData.getVehiculeDayCost() + fileData.getVehiculeCost()
        //        + dist*fileData.getDistanceCost();
        
        
        
    }
    
}
