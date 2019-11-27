package com.bitly.functional;

import com.bitly.utils.PropertiesFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class UserInfoSteps extends BaseSteps {

	private String accessToken;
	private Response response;
	private RequestSpecification request;
	private ValidatableResponse json;

	@Given("valid user credentials are available")
	public void valid_credentials() {
		// get accessToken from the test data file
		accessToken = PropertiesFileReader	.getProperties()
											.getProperty("accessToken");

	}

	@When("^I make a request to the '(.+)' endpoint")
	public void valid_request_to_user_info(String endpointName) {

		RestAssured.baseURI = BASE_URL;

		request = given()	.param("access_token", accessToken)
							.basePath("/")
							.log()
							.all()
							.contentType(ContentType.JSON);

		response = request	.when()
							.get(getEndpointName(endpointName));
	}

	@Then("I should get a successful response")
	public void successful_response() {
		response.then()
				.log()
				.all()
				.statusCode(200);
	}

	@Then("the status code of the response is (\\d+)")
	public void verify_status_code(int statusCode) {
		response.then()
				.log()
				.all()
				.statusCode(statusCode);
	}

	// helper methods

	public String getEndpointName(String endpointString) {
		switch (endpointString) {
		case "User Info":
			return USERINFO_ENDPOINT;

		default:
			return null;
		}

	}

}
