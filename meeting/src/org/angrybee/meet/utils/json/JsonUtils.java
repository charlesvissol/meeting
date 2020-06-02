package org.angrybee.meet.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	
	public static String toJson(Object pojo) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	
}
