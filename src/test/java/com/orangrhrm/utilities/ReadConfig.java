package com.orangrhrm.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class ReadConfig {
	public static Properties prop;
	
	public ReadConfig(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./Configuration/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String getTire() {
		String tire = prop.getProperty("tire");
		return tire;
	}

}
