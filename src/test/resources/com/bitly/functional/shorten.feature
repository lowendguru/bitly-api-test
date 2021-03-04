Feature: Shorten endpoint 


Scenario: successful request to Shorten endpoint with random long URL value 
	Given access token is valid 
	And a random long url is available as parameter 
	When I make a request to the 'Shorten' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 200 
	And the response body should contain attribute 'status_txt' with text value 'OK' 
	And the response body should contain attribute 'data.long_url' with text random_long_url 
	And the response body should contain attribute 'data.url' with text value not empty


	Scenario: successful request to Shorten endpoint with random long URL value
		Given access token is missing
		When the number value of the attribute '<string>' has been incremented by <number>

	Scenario: successful request to Shorten endpoint with specific long URL value 
	Given access token is valid 
	And a specific long url 'http://test.com/' is available as parameter 
	When I make a request to the 'Shorten' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 200 
	And the response body should contain attribute 'status_txt' with text value 'OK' 
	And the response body should contain attribute 'data.long_url' with text value 'http://test.com/' 
	And the response body should contain attribute 'data.url' with text value not empty 
	
	
Scenario: unsuccessful request to Shorten endpoint with invalid long URL value 
	Given access token is valid 
	And a specific long url 'this_is_an_invalid_url' is available as parameter 
	When I make a request to the 'Shorten' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 500 
	And the response body should contain attribute 'status_txt' with text value 'INVALID_URI' 
	
	
Scenario: unsuccessful request to Shorten endpoint with missing long URL value 
	Given access token is valid 
	And a specific long url '' is available as parameter 
	When I make a request to the 'Shorten' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 500 
	And the response body should contain attribute 'status_txt' with text value 'MISSING_ARG_URI' 
	
	
	
	