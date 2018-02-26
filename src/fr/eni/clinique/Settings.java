package fr.eni.clinique;

import java.util.Properties;

public class Settings {

	private static Properties prop;
	
	static {
		prop = new Properties();
		try {
			prop.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) 
	{
		return prop.getProperty(key);
	}
}


