package com.barchart.translator.common.config.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.mrbean.MrBeanModule;
import org.codehaus.jackson.type.TypeReference;

import com.barchart.translator.common.config.NetworkPoint;
import com.barchart.translator.common.config.NetworkPoints;

public class ConfigLoader {

	public NetworkPoints loadNetworkPoints(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule simpleModule = new SimpleModule("MyModule", new Version(1, 0, 0, null));
		mapper.registerModule(simpleModule);
		mapper.registerModule(new MrBeanModule());
		
//		NetworkPoints points = mapper.readValue(inputStream, NetworkPoints.class);
		
		TypeReference<HashMap<String, NetworkPoint>> typeRef = new TypeReference<HashMap<String, NetworkPoint>>() {
			
		};
		
		
		Map<String, NetworkPoint> readValue = mapper.readValue(inputStream, typeRef);
		System.out.println(readValue.toString());
		System.out.println("Class:  " + readValue.getClass());
		NetworkPoints networkPoints = new NetworkPoints(readValue);
		return networkPoints;
	}
	
	
	
	private static class NetworkPointsDeserializer extends JsonDeserializer<NetworkPoints> {
		@Override
		public NetworkPoints deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			for (int i = 0; i < 3; i++) {
				String text = jp.getText();
				System.out.println(text);
			}
//			try {
//				String[] tokens = text.split(":");
//				int hours = Integer.parseInt(tokens[0]);
//				int minutes = Integer.parseInt(tokens[1]);
//				int seconds = Integer.parseInt(tokens[2]);
//				return new LocalTime(hours, minutes, seconds);
//			} catch (NumberFormatException e) {
//				throw new RuntimeException("Time format is hh:mm:ss (24-hour clock).  Could not parse: " + text);
//			}
			return null;
		}
	}
}
