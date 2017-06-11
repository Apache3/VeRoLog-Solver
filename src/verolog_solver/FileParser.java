/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verolog_solver;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Apache
 */
public class FileParser {
    
    private String fileName;

    
    public FileParser(String fileName)
    {
        this.fileName = fileName;
        
    }
    
    public void parse()
    {
        String fileContent = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                fileContent = sb.toString();
            }
            catch( Exception e)
            {
                e.printStackTrace();
            }
            finally {
                br.close();
            }
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }
        
        
        String[] lines = fileContent.split("[\n\r]+");
        
        /*for (int i = 0 ; i < lines.length ; i++)
        {
            System.out.println(lines[i]);
        }*/
        
        FileData fileData = FileData.getInstance();
        
        for ( int i = 0; i < lines.length ; i++)
        {
            String[] data = lines[i].split(" = ");
            
            if ( data[0].equals("DATASET"))
            {
                fileData.setDataset(data[1]);
            }
            
            if ( data[0].equals("NAME"))
            {
                fileData.setName(data[1]);
            }
            
            if ( data[0].equals("DAYS"))
            {
                fileData.setDays(Integer.parseInt(data[1]));
            }
            
            if ( data[0].equals("CAPACITY"))
            {
                fileData.setCapacity(Integer.parseInt(data[1]));
            }
            
            if ( data[0].equals("MAX_TRIP_DISTANCE"))
            {
                fileData.setMaxTripDistance(Integer.parseInt(data[1]));
            }
            
            if ( data[0].equals("DEPOT_COORDINATE"))
            {
                fileData.setDepotCoordinate(Integer.parseInt(data[1]));
            }
            
            if ( data[0].equals("VEHICULE_COST"))
            {
                fileData.setVehiculeCost(Integer.parseInt(data[1]));
            }
            
            if ( data[0].equals("VEHICULE_DAY_COST"))
            {
                fileData.setVehiculeDayCost(Integer.parseInt(data[1]));
            }
            
            if ( data[0].equals("DISTANCE_COST"))
            {
                fileData.setDistanceCost(Integer.parseInt(data[1]));
            }
            
            if ( data[0].equals("TOOLS"))
            {
                int nbTools = Integer.parseInt(data[1]);
                fileData.setNbTools(nbTools);
                
                for (int j = 1; j <= nbTools ; j++  )
                {
                    String[] toolData = lines[ i + j ].split("[ ]+");
                    int toolID     = Integer.parseInt(toolData[0]);
                    int toolSize   = Integer.parseInt(toolData[1]);
                    int toolNumber = Integer.parseInt(toolData[2]);
                    int toolCost   = Integer.parseInt(toolData[3]);
                    for (int k = 0 ; k < toolNumber ; k++)
                    {
                        fileData.createTool(toolID, toolSize, toolCost);
                    }
                }
                i+=nbTools;
                
            }
            
            if ( data[0].equals("COORDINATES"))
            {
                int nbLocations = Integer.parseInt(data[1]);
                fileData.setNbCoordinates(nbLocations);
                
                for (int j = 1; j <= nbLocations ; j++  )
                {
                    String[] locationData = lines[ i + j ].split("[ ]+");
                    int locationID = Integer.parseInt(locationData[0]);
                    int x          = Integer.parseInt(locationData[1]);
                    int y          = Integer.parseInt(locationData[2]);
                    fileData.createLocation(locationID, x, y);
                }
                i+= nbLocations;
            }
            
            if ( data[0].equals("REQUESTS"))
            {
                int nbRequests = Integer.parseInt(data[1]);
                fileData.setNbRequests(nbRequests);
                
                for (int j = 1; j <= nbRequests ; j++  )
                {
                    String[] requestData = lines[ i + j ].split("[ ]+");
                    int requestID  = Integer.parseInt(requestData[0]);
                    int customerID = Integer.parseInt(requestData[1]);
                    int firstDay   = Integer.parseInt(requestData[2]);
                    int lastDay    = Integer.parseInt(requestData[3]);
                    int nbDays     = Integer.parseInt(requestData[4]);
                    int toolID     = Integer.parseInt(requestData[5]);
                    int nbTools    = Integer.parseInt(requestData[6]);
                    
                    fileData.createRequest(requestID, customerID, firstDay, 
                                           lastDay, nbDays, toolID, nbTools);
                    
                }
                i+= nbRequests;
            }
        }
        
    }
    
   
}
