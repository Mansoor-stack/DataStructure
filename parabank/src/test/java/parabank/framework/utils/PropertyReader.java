package parabank.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import parabank.framework.constants.UIConstants;

public class PropertyReader {
	
	private Properties prop;
	FileInputStream fis;
	
	String userDir = System.getProperty("user.dir");
	
	public PropertyReader() {
		prop  = new Properties();
		File file = new File(userDir + UIConstants.DEFAULT_CONFIG_FILE);
		loadProperties(file);
	}
	
	public PropertyReader(String filePath) {
		prop  = new Properties();
		File file = new File(userDir + filePath);
		loadProperties(file);
	}
	
	private void loadProperties(File file) {
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String  getProperty(String key) {
		return prop.getProperty(key);
	}
	
	public HashMap<String, String> getProperties(){
		Set<Object> keys = prop.keySet();
		HashMap<String, String> keyValuePairs = new HashMap<String, String>();
		for(Object obj: keys) {
			String key = (String) obj;
			String value = getProperty(key);
			keyValuePairs.put(key, value);
		}
		System.out.println(keyValuePairs);
		return keyValuePairs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public PropertyReader() {
		String defaultConfigFile = UIConstants.DEFAULT_CONFIG_FILE;
		prop = new Properties();
		loadProperties(defaultConfigFile);
	}
	
	public PropertyReader(String fileName) {
		prop = new Properties();
		loadProperties(fileName);
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void loadProperties(String fileName) {
		try {
			String fileRoot = System.getProperty("user.dir");
			File file = new File(fileRoot + fileName);
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);

			FileReader reader = new FileReader(fileRoot + fileName);
			prop.load(reader);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public HashMap<String, String> getProperties1(){
		HashMap<String, String> propertiesMap = new HashMap<String, String>();
		Set<Object> keys = prop.keySet();
		String value; 
		for(Object obj: keys) {
			String key = (String) obj;
			value = getProperty(key);
			propertiesMap.put(key, value);
		}
		return propertiesMap;
	}
}
