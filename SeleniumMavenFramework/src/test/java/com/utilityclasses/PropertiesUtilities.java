package com.utilityclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import base.BaseTest;

public class PropertiesUtilities {
	FileInputStream stream= null;
	Properties propertyFile= null;
	
	
	public Properties loadFile(String filename) {
		String filePath=null; ;
		 propertyFile= new Properties();
		switch (filename) {
		case "applicationDataProperties":
			filePath=Constants.APPLICATION_DATA_PATH;
			break;
		case "usermenuDataProperties":
			filePath=Constants.USER_MENU_DATA_PATH;
		case "createAccountDataProperties":
			filePath= Constants.CREATE_ACCOUNT_DATA_PATH;

		default:
			break;
		}
		try {
			stream= new FileInputStream(filePath);
			propertyFile.load(stream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return propertyFile;
	}
	
	
	public String getPropertyData(String key) {
		String value= propertyFile.getProperty(key);
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	
	
	
	
	
	
	
	
		
	
}
