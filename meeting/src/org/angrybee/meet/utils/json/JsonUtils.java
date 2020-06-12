package org.angrybee.meet.utils.json;

import java.io.File;
import java.io.IOException;

import org.angrybee.meet.utils.data.User;
import org.angrybee.meet.utils.io.IOUtils;
import org.angrybee.meet.utils.net.IPUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	/**
	 * Translate an object into Json String content
	 * @param pojo Object to translate in Json content
	 * @return String representation of Json content
	 */
	public static String toJsonString(Object pojo) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	/**
	 * Write an object to a file (Json file)
	 * @param path Full path of the file
	 * @param pojo Object to convert to a Json file
	 */
	public static void toJsonFile(String path, Object pojo) {
		
		String content = JsonUtils.toJsonString(pojo);
		
		IOUtils.writeText(content, path);
		
	}

	
	
	/**
	 * Convert a Json file content to an object
	 * @param path Full path of the Json file
	 * @param obj Object to load
	 * @return the object its data
	 */
	public static Object fromJsonFile(String path, Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			obj = mapper.readValue(new File(path), obj.getClass());

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	/**
	 * Convert Json string content to an object
	 * @param content String content in Json format
	 * @param obj Object to load
	 * @return Object with loaded Json content
	 */
	public static Object fromJsonString(String content, Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			obj = mapper.readValue(content, obj.getClass());

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	
	public static void main(String argv[]) {
		
		User user = new User();
		
		
		user.setDisplayname("Charles Vissol");
		user.setFirstname("Charles");
		user.setLastname("Vissol");
		user.setId("A094614");
		user.setIpAddress(IPUtils.getIp());
		user.setNetworkInterfaces(IPUtils.getNetworkInterfaces());
		
		JsonUtils.toJsonFile("/home/vissol/Downloads/user.json", user);
		
		
		
		user = (User) JsonUtils.fromJsonFile("/home/vissol/Downloads/user.json", user);
		
		System.out.println(user.toString());
		
		String userJson = user.toString();
		
		User user2 = new User();
		
		user2 = (User) JsonUtils.fromJsonString(userJson, user2);
		
		
		System.out.println(user2.hashCode());
		System.out.println(user2.toString());
		
		
		
	}
	
	
}
