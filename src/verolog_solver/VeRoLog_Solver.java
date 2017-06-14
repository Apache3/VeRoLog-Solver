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
        FileParser fileParser = new FileParser("Data/Data1.txt");
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
        trip.addStop(locations.get(1), rqList.get(0), true);
        trip.goToDepot();
        
        
        System.out.println("--------PRINT OF A TRIP");
        System.out.println(trip.toString(true));
       //FileData fileData = fileParser.getFileData();
        //System.out.println(fileData.toString(false));
        //ArrayList<Location> locations = fileData.getLocations();
        //int dist = locations.get(6).distanceTo(locations.get(0));
        //System.out.println("distance 0 -> 1: " + dist);
        //int cost = fileData.getVehiculeDayCost() + fileData.getVehiculeCost()
        //        + dist*fileData.getDistanceCost();
        
        
        
    }
    
}
