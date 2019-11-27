Feature: All endpoints require an access token 


Scenario Outline: unsuccessful request to endpoint with wrong access token 
	Given access token is <AccessToken> 
	When I make a request to the '<Endpoint>' endpoint 
	Then the status code of the response is 200 
	And the response body should contain attribute 'status_code' with number value <StatusCode> 
	And the response body should contain attribute 'status_txt' with text value '<StatusText>' 
	
	Examples: 
		|AccessToken|Endpoint|StatusCode|StatusText|
		|invalid|User Info|403|INVALID_ACCESS_TOKEN|
		|missing|User Info|500|MISSING_ARG_ACCESS_TOKEN|
		|invalid|User Link History|403|INVALID_ACCESS_TOKEN|
		|missing|User Link History|500|MISSING_ARG_ACCESS_TOKEN|
		|invalid|Shorten|500|INVALID_ARG_ACCESS_TOKEN|
		|missing|Shorten|500|MISSING_ARG_ACCESS_TOKEN|
