package org.angrybee.meet.utils.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.angrybee.meet.utils.io.IOUtils;

/**
 * Class to serialize locally the user's informations 
 * @author vissol
 *
 */
public class Serializer {

	/**
	 * Serialize User object in a file. Override file.
	 * @param serObj User object
	 * @param filepath Full file path of the serialized object
	 */
    public static void writeUser(User serObj, String filepath) {
    	 
        try {
        	System.out.println(filepath);
        	new File(filepath).createNewFile();//Create file
        	
        	
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }	


    /**
     * Deserialization of the User object
     * @param filepath Full file path of the serialized object file
     * @return User object loaded from file
     */
    public static User readUser(String filepath) {
   	 
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
    
    public static void main(String argv[]) {
    	
    	
    	IOUtils.createHomeDir();
    	
    	String home = IOUtils.getHomeDirPath();
    	
    	User user = new User();
    	
    	user.setId("A094614");
    	user.setFirstname("Charles");
    	user.setLastname("Vissol");
    			
    	Serializer.writeUser(user, home + File.separator + "User.ser");
    	
    	
    	
    	User user2 = Serializer.readUser(IOUtils.getHomeDirPath() + File.separator + "User.ser");
    	
    	System.out.println(user2.getId() + " " + user2.getFirstname() + " " + user2.getLastname());
    	
    	
    	
    	
    }
    
    
}
