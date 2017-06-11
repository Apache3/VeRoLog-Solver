/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.util.Iterator;

/**
 *
 * @author Apache
 */
public class Depot extends Location{
    
    
    
    public Depot(int ID, int x, int y){
        super(ID, x, y);
    }
   
    public Depot(Location loc)
    {
        super(loc.getID(), (int)loc.getPosition().getX(), (int)loc.getPosition().getY());
    }
    
    public int getNbRemainingTools(int ID)
    {
        int remainingTools = 0;
        Iterator<Tool> it = toolList.iterator();
        
        while ( it.hasNext() )
        {
            if ( it.next().getID() == ID )
            {
                remainingTools++ ;
            }
        }
        return remainingTools;
    }
    
}
