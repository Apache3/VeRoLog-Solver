/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;

/**
 *
 * @author Apache
 */
public class Tool {
    private final int ID;
    private final int size;
    //private static int nb_available;
    private final int cost;
    
    public Tool(int ID,int size, int cost)
    {
        this.ID = ID;
        this.size = size;
        this.cost = cost;
    }

    public int getID() {
        return ID;
    }

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
        
        return str;
    }
}
