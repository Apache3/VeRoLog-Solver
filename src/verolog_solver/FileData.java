/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author Apache
 */
public class FileData {
    
    private static FileData INSTANCE;
    
    private String dataset;
    private String name;
    private int days;
    private int capacity;
    private int maxTripDistance;
    private int depotCoordinate;
    private int vehiculeCost;
    private int vehiculeDayCost;
    private int distanceCost;
    private int nbTools;
    private ArrayList<Tool> toolList;
    private int nbCoordinates;
    private ArrayList<Location> locations; 
    private int nbRequests;
    private ArrayList<Request> requests;
    
    
    private FileData()
    {
        dataset = "";
        name = "";
        days = 0 ;
        capacity = 0;
        maxTripDistance = 0;
        depotCoordinate = 0;
        vehiculeCost = 0;
        vehiculeDayCost = 0;
        distanceCost = 0;
        nbTools = 0;
        toolList = new ArrayList<Tool>();
        nbCoordinates = 0;
        locations = new ArrayList<Location>(); 
        nbRequests = 0;
        requests = new ArrayList<Request>();
    }
    
    public static FileData getInstance()
    {
        if ( INSTANCE == null)
        {
            INSTANCE = new FileData();
        }
        
        return INSTANCE;
    }
    
    public void setDataset(String dataset)
    {
        this.dataset = dataset;
    }
    
    public String getDataset() {
        return dataset;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMaxTripDistance() {
        return maxTripDistance;
    }

    public void setMaxTripDistance(int maxTripDistance) {
        this.maxTripDistance = maxTripDistance;
    }

    public int getDepotCoordinate() {
        return depotCoordinate;
    }

    public void setDepotCoordinate(int depotCoordinate) {
        this.depotCoordinate = depotCoordinate;
    }

    public int getVehiculeCost() {
        return vehiculeCost;
    }

    public void setVehiculeCost(int vehiculeCost) {
        this.vehiculeCost = vehiculeCost;
    }

    public int getVehiculeDayCost() {
        return vehiculeDayCost;
    }

    public void setVehiculeDayCost(int vehiculeDayCost) {
        this.vehiculeDayCost = vehiculeDayCost;
    }

    public int getDistanceCost() {
        return distanceCost;
    }

    public void setDistanceCost(int distanceCost) {
        this.distanceCost = distanceCost;
    }

    public int getNbTools() {
        return nbTools;
    }

    public void setNbTools(int nbTools) {
        this.nbTools = nbTools;
    }
    
    public void createTool(int ID, int size, int cost)
    {
        Tool newTool = new Tool(ID, size, cost);
        toolList.add(newTool);
    }

    public ArrayList<Tool> getToolList() {
        return toolList;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
    
    public void createLocation(int ID, int x, int y){
        Location newLocation = new Location(ID, x, y);
        locations.add( newLocation );
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public int getNbRequests() {
        return nbRequests;
    }
    
    public void createRequest(int requestID, int customerID, int firstDay, 
                              int lastDay, int nbDays, int toolID, int nbTools)
    {
        Request newRequest = new Request(requestID, customerID, firstDay, 
                               lastDay, nbDays, toolID, nbTools);
        requests.add(newRequest);
    }

    public void setNbRequests(int nbRequests) {
        this.nbRequests = nbRequests;
    }
    
    public void setNbCoordinates(int nbCoordinates) {
        this.nbCoordinates = nbCoordinates;
    }
    
    public int getNbCoordinates() {
        return nbCoordinates;
    }
    
    public String toString()
    {
        String str;
        
        str = "=========DATAFILE CONTENT===========";
        str += "\n\nDataset: " + dataset;
        str += "\nName: " + name;
        str += "\nDays: " + days;
        str += "\nCapacity: " + capacity;
        str += "\nMax Trip Distance: " + maxTripDistance;
        str += "\nDepot Coordinate: " + depotCoordinate;
        str += "\nVehicule cost: " + vehiculeCost;
        str += "\nVehicule cost per day: " + vehiculeDayCost;
        str += "\nDistance cost: " + distanceCost;
        str += "\nNumber of tools: " + nbTools;
        str += "\nSize of tool list: " + toolList.size();
        str += "\nNumber of Coordinates: " + nbCoordinates;
        str += "\nSize of location list: " + locations.size();
        str += "\nNumber of Requests: " + nbRequests;
        str += "\nSize of Requests list: " + requests.size();
        
        
        
        return str;
    }
    
    public String toString(boolean printLists)
    {
        String str = this.toString();
        
        if (printLists)
        {
            
            str += "\nTool list\n";
            str += toolList.toString();
            
            str += "\nLocations list\n";
            str += locations.toString();
            
            str += "\nRequests list\n";
            str += requests.toString();
        }
        
        return str;
    }
            
}
