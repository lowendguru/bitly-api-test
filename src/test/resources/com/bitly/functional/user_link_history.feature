Feature: User Link History endpoint 


Scenario: successful request to User Link History endpoint 
	Given access token is valid 
	When I make a request to the 'User Link History' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 200 
	And the response body should contain attribute 'status_txt' with text value 'OK' 
	And the response body should contain attribute 'data.result_count' with number value not 0 
	

Scenario: successful request to User Link History endpoint incrementing number of links
	Given access token is valid 
	When I make a request to the 'User Link History' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value 200 
	And the response body should contain attribute 'status_txt' with text value 'OK' 
	When I get the number value of the attribute 'data.result_count'
	And a random long url is available as parameter
	And I make a request to the 'Shorten' endpoint 
	Then the status code of the response is 200
	And the response body should contain attribute 'data.url' with text value not empty
	When I wait for 3 seconds 
	And I make a request to the 'User Link History' endpoint 
	Then the status code of the response is 200 
	And the number value of the attribute 'data.result_count' has been incremented by 1