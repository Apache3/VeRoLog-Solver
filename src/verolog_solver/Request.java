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
public class Request {
    
    private final int requestID;
    private final int customerID;
    private final int firstDay;
    private final int lastDay;
    private final int nbDays;
    private final int toolID;
    private final int nbTools;
    
    public Request(int requestID, int customerID, int firstDay, int lastDay,
                   int nbDays, int toolID, int nbTools)
    {
        this.requestID = requestID;
        this.customerID = customerID;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.nbDays = nbDays;
        this.toolID = toolID;
        this.nbTools =nbTools;
    }
    
    public static ArrayList<Request> orderByFirstDay(ArrayList<Request> requests)
    {
        ArrayList<Request> orderedRequests = new ArrayList<>();
        
        while (!requests.isEmpty())
        {
            int minDayIndex = 0;
            for (int i = 0 ; i < requests.size() ; i++)
            {   
                int currentFirstDay = requests.get(i).getFirstDay();
                int minFirstDay = requests.get(minDayIndex).getFirstDay();
                
                if ( currentFirstDay < minFirstDay )
                {
                    minDayIndex = i;
                }
            }    
            orderedRequests.add( requests.remove(minDayIndex) );
        }
        
        
        return orderedRequests;
    }

    public int getRequestID() {
        return requestID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public int getLastDay() {
        return lastDay;
    }

    public int getNbDays() {
        return nbDays;
    }

    public int getToolID() {
        return toolID;
    }

    public int getNbTools() {
        return nbTools;
    }
    
    public String toString(){
    String str;
    
    str = "\nRequest ID: " + requestID + "\n";
    str+= "Customer ID: " + customerID + "\n";
    str+= "First Day: " + firstDay + "\n";
    str+= "Last Day: " + lastDay + "\n";
    str+= "Nb Days: " + nbDays + "\n";
    str+= "Tools ID: " + toolID + "\n";
    str+= "Nb Tools: " + nbTools + "\n";
    
    return str;
    }
}