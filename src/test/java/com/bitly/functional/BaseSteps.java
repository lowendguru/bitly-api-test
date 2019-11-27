package com.bitly.functional;

import static io.restassured.RestAssured.given;

import com.bitly.utils.PropertiesFileReader;

import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/*
 * This class has common constants and methods.
 */

public class BaseSteps {

	protected String BASE_URL = getProperty("secureProtocol") + getProperty("baseApiUrl") + getProperty("apiVersion");
	protected String USERINFO_ENDPOINT = getProperty("userInfoEndpoint");
	
	protected String accessToken;
	protected Response response;
	protected RequestSpecification request;
	protected ValidatableResponse json;

	// helper methods

	protected String getEndpointName(String endpointString) {
		switch (endpointString) {
		case "User Info":
			return USERINFO_ENDPOINT;

		default:
			return null;
		}

	}

	protected String getProperty(String propertyName) {
		return PropertiesFileReader	.getProperties()
									.getProperty(propertyName);
	}

}
