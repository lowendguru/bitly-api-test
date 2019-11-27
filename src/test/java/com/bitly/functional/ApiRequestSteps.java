package com.bitly.functional;

import static io.restassured.RestAssured.given;

import org.hamcrest.core.IsEqual;

import com.bitly.utils.PropertiesFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiRequestSteps extends BaseSteps {

	@Given("access token is valid")
	public void valid_access_token() {
		// get accessToken from the test data file
		accessToken = PropertiesFileReader	.getProperties()
											.getProperty("accessToken");

	}

	@Given("access token is invalid")
	public void invalid_access_token() {

		accessToken = "invalid_access_token";

	}

	@Given("access token is missing")
	public void missing_access_token() {
		accessToken = "";

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

	@Then("^the response body should contain attribute '(.+)' with text value '(.+)'")
	public void response_body_contains_attribute_and_value(String attribute, String value) {
		response.then()
				.log()
				.all()
				.body(attribute, IsEqual.equalTo(value));

	}

	@Then("^the response body should contain attribute '(.+)' with number value (\\d+)")
	public void response_body_contains_attribute_and_value(String attribute, int value) {
		response.then()
				.log()
				.all()
				.body(attribute, IsEqual.equalTo(value));

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

}
