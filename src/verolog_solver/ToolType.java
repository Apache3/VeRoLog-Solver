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
public class ToolType {
    private final int ID;
    private final int size;
    private int remainingTools;
    private final int cost;
    
    public ToolType(int ID,int size, int cost, int nbTools)
    {
        this.ID = ID;
        this.size = size;
        this.cost = cost;
        this.remainingTools = nbTools;
    }

    public static ToolType getByID(int ID, ArrayList<ToolType> toolList)
    {
        ToolType toolType = null;
        for (int i = 0 ; i < toolList.size() ; i++)
        {
            if (toolList.get(i).getID() == ID)
            {
                toolType = toolList.get(i);
            }
        }
        
        return toolType;
    }
    
    public int getID() {
        return ID;
    }

    public int getRemainingTools() {
        return remainingTools;
    }
    
    /*public void addTools(int nbTools)
    {
        this.remainingTools +=nbTools;
    }*/

    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }
    
    public String toString(){
        String str;
        
        str = "\nTool ID: " + ID + "\n";
        str+= "Size: " + size + "\n"; 
        str+= "Cost: " + cost + "\n";
        str+= "Nb Tools: " + remainingTools + "\n";
        
        return str;
    }
}
