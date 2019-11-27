package com.bitly.functional;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;

import com.bitly.utils.PropertiesFileReader;
import com.bitly.utils.RandomValueGenerator;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiRequestSteps extends BaseSteps {

	@Before
	public void setupTest() {
		RestAssured.baseURI = BASE_URL;
		request = given()	.log()
							.all();
	}

	@Given("access token is valid")
	public void valid_access_token() {
		// get accessToken from the test data file
		accessToken = PropertiesFileReader	.getProperties()
											.getProperty("accessToken");
	}

	@Given("^a specific long url '(.*?)' is available as parameter")
	public void long_url_parameter(String longUrl) {
		longUrlParam = longUrl;
		request.param("longUrl", longUrlParam);
	}

	@Given("a random long url is available as parameter")
	public void random_long_url_parameter() {
		long_url_parameter("https://www.google.com/search?q=" + RandomValueGenerator.getRandomText(20));
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
	public void valid_request_to_endpoint(String endpointName) {

		request	.param("access_token", accessToken)
				.basePath("/")
				.contentType(ContentType.JSON)
				.log()
				.all();

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

	@Then("^the response body should contain attribute '(.+)' with text value not empty")
	public void response_body_contains_attribute_and_value_not_null(String attribute) {
		response.then()
				.log()
				.all()
				.body(attribute, IsNot.not(""));
	}

	@Then("the response body should contain attribute '(.+)' with text random_long_url")
	public void response_body_contains_attribute_and_long_url(String attribute) {
		response_body_contains_attribute_and_value(attribute, longUrlParam);
	}

	@Then("^the response body should contain attribute '(.+)' with number value (\\d+)")
	public void response_body_contains_attribute_and_value(String attribute, int value) {
		response.then()
				.log()
				.all()
				.body(attribute, IsEqual.equalTo(value));
	}

	@Then("^the response body should contain attribute '(.+)' with number value not (\\d+)")
	public void response_body_not_contains_attribute_and_value(String attribute, int value) {
		response.then()
				.log()
				.all()
				.body(attribute, IsNot.not(value));
	}

	@When("I get the number value of the attribute '(.+)'")
	public void get_number_value_from_attribute(String attribute) {
		numberValue = response	.then()
								.log()
								.all()
								.extract()
								.path(attribute);
	}

	@Then("the number value of the attribute '(.+)' has been incremented by (\\d+)")
	public void number_has_incremented(String attribute, int increment) {
		int newNumberValue = response	.then()
										.log()
										.all()
										.extract()
										.path(attribute);

		assertTrue(newNumberValue == numberValue + increment);
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
	
	// workaround due to slow endpoint update 
	@When("I wait for (\\d+) seconds")
	public void wait_for_seconds(int seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
