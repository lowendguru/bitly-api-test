package com.bitly.functional;

import com.bitly.utils.PropertiesFileReader;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * This class has common constants and methods.
 */

public class BaseSteps {

	protected String BASE_URL = getProperty("secureProtocol") + getProperty("baseApiUrl") + getProperty("apiVersion");
	protected String USER_INFO_ENDPOINT = getProperty("userInfoEndpoint");
	protected String USER_LINK_HISTORY_ENDPOINT = getProperty("userLinkHistoryEndpoint");
	protected String SHORTEN_ENDPOINT = getProperty("shortenEndpoint");

	protected String accessToken;
	protected Response response;
	protected RequestSpecification request;
	protected int numberValue;
	protected String longUrlParam;

	// helper methods

	protected String getEndpointName(String endpointString) {
		switch (endpointString) {
		case "User Info":
			return USER_INFO_ENDPOINT;
		case "User Link History":
			return USER_LINK_HISTORY_ENDPOINT;
		case "Shorten":
			return SHORTEN_ENDPOINT;

		default:
			return null;
		}

	}

	protected String getProperty(String propertyName) {
		return PropertiesFileReader	.getProperties()
									.getProperty(propertyName);
	}

}
