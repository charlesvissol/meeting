package org.angrybee.meet.utils.json;

import java.io.File;
import java.io.IOException;

import org.angrybee.meet.utils.io.IOUtils;

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
	
	
	
	
}
