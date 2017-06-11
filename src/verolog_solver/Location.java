/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Apache
 */
public class Location {
    
    protected final int ID;
    protected final Point2D.Double position;
    protected ArrayList<Tool> toolList;
    
    
    public Location(int ID,int x, int y)
    {
        this.ID = ID;
        position = new Point2D.Double((double)x,(double)y);
        
    }
   
    
    public int distanceTo(Location location)
    {
        int dist = (int)position.distance(location.getPosition());
        return dist;
    }

    public int getID() {
        return ID;
    }

    public Point2D.Double getPosition() {
        return position;
    }
    
    public ArrayList<Tool> getToolList() {
        return toolList;
    }
    
    public void addTools(ArrayList<Tool> toolList)
    {
        this.toolList.addAll(toolList);
    }
    
    public void addTool(Tool tool)
    {
        this.toolList.add(tool);
    }
    
    public String toString(){
        String str;
        
        str = "\nID: " +ID;
        str+= "\nx: " + position.getX();
        str+= "\ny: " + position.getY() + "\n\n";
        
        return str;
    }
}
