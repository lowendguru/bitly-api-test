Feature: User Link History endpoint 


Scenario: successful request to User Link History endpoint 
	Given access token is valid 
	When I make a request to the 'User Link History' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 200 
	And the response body should contain attribute 'status_txt' with text value 'OK' 
	And the response body should contain attribute 'data.result_count' with number value not 0 
