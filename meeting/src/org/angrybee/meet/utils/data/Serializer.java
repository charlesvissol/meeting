package org.angrybee.meet.utils.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class to serialize locally the user's informations 
 * @author vissol
 *
 */
public class Serializer {

	
    public static void writeObject(User serObj, String filepath) {
    	 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }	

    
    public static User readObject(String filepath) {
   	 
    	User serObject = null;
    	
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            serObject = (User) objectIn.readObject();
            objectIn.close();
 
            return serObject;
            
        } catch (Exception ex) {
        	
            ex.printStackTrace();
        }
        
        return serObject;
        
    }	
    
    
    
    
}
