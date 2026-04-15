package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	// Here we will read the Properties file from src/test/resources/config/config.properties
	
	private ConfigManager(){
		// private constructor helps to avoid object creation of ConfigManager
	}
	
	private static Properties prop = new Properties();
	private static String path = "config/config.properties";
	private static String env;
	static {
		
		env=System.getProperty("env","dev");
		env=env.toLowerCase().trim();
		
		switch(env) {
		
		case "dev" -> path = "config/config.properties";
		case "qa" -> path = "config/config.qa.properties";
		case "uat" -> path = "config/config.uat.properties";
		default ->  path="config/config.properties";
		
	}
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		if(input == null) {
			throw new RuntimeException("Cannot find file on given path --> "+path);
		}
		
		
		try {
			prop.load(input);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getProperty(String key) throws IOException {
		return prop.getProperty(key);
	}
}
