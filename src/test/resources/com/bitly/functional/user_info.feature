Feature: User Info endpoint 


Scenario: successful request to User Info endpoint 
	Given access token is valid 
	When I make a request to the 'User Info' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 200 
	And the response body should contain attribute 'status_txt' with text value 'OK' 
	And the response body should contain attribute 'data.full_name' with text value 'elseviertests' 
	
Scenario: unsuccessful request to User Info endpoint with invalid access token 
	Given access token is invalid 
	When I make a request to the 'User Info' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 403 
	And the response body should contain attribute 'status_txt' with text value 'INVALID_ACCESS_TOKEN' 
	
Scenario: unsuccessful request to User Info endpoint with missing access token 
	Given access token is missing 
	When I make a request to the 'User Info' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 500 
	And the response body should contain attribute 'status_txt' with text value 'MISSING_ARG_ACCESS_TOKEN'