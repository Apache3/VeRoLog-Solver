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
public class SolutionPlanner {
    
    public LinkedList<Solution> planSolutions(ArrayList<Request> requestList)
    {
        //get iterator on ordered list
        ArrayList<Request> rList = Request.orderByFirstDay(requestList);
        Iterator<Request> it = rList.iterator();
        
        while (it.hasNext())
        {
            Request request = it.next();
            int reqIndex = rList.indexOf(request);
            while ( (reqIndex >=0 && reqIndex < rList.size()) )
            {
                
            }
        }
    }
}
