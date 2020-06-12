package org.angrybee.meet.utils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Set of utility methods for input/output
 * @author Charles Vissol
 *
 */
public class IOUtils {
	
	/**
	 * Home directory of the application
	 */
	private static String HOME_DIR = "meeting";
	
	/**
	 * User home directory
	 */
	private static String USER_HOME = System.getProperty("user.home");
	
	/**
	 * Preference file
	 */
	private static String PREF_FILE = "meeting.json";
	
	/**
	 * Check if root directory of the meeting application exists
	 * @return True if directory exists, false if directory does not exist
	 */
	public static boolean homeDirExists() {
		
		String meetingHomeDir = USER_HOME + File.separator + HOME_DIR;
		
		File meetingHomeDirFile = new File(meetingHomeDir);
		
		if(meetingHomeDirFile.exists())
			return true;
		else
			return false;
	}
	
	/**
	 * Returns the full path of the application home directory
	 * @return Path of home directory in String
	 */
	public static String getHomeDirPath() {
		return USER_HOME + File.separator + HOME_DIR;
	}
	
	
	/**
	 * Create the application directory
	 */
	public static void createHomeDir() {
		
		String meetingHomeDir = USER_HOME + File.separator + HOME_DIR;
		
		new File(meetingHomeDir).mkdir();
		
	}
	
	/**
	 * Check if the preference file exists
	 * @return True if it exists and contains the right information. If not return false.
	 */
	public static boolean checkPref() {
		
		String meetingPref = USER_HOME + File.separator + HOME_DIR + File.separator + PREF_FILE;
		
		boolean fileExists = new File(meetingPref).exists();
		
		
		//TODO: check content
		
		return true;
		
	}
	
	/**
	 * Write in a file a String content in one time
	 * @param content String content to write into text file
	 * @param path Full path of the file to write
	 */
	public static void writeText(String content, String path) {
		
		File file = new File(path);

		
		try {
			if(!file.exists())
				file.createNewFile();
			
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			
			out.append(content);
			out.flush();
			out.close();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
}
