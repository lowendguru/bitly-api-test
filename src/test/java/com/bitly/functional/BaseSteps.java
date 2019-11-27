package com.bitly.functional;

import com.bitly.utils.PropertiesFileReader;

/*
 * This class has common constants and methods.
 */

public class BaseSteps {

	protected String BASE_URL = getProperty("secureProtocol") + getProperty("baseApiUrl") + getProperty("apiVersion");
	protected String USERINFO_ENDPOINT = getProperty("userInfoEndpoint");
	

	
	protected String getProperty(String propertyName) {
		return PropertiesFileReader.getProperties().getProperty(propertyName);
	}

}
