Feature: User Info endpoint

 Background: Existing user credentials
    Given valid user credentials are available

  Scenario: successful call to User Info
    When I make a request to the 'User Info' endpoint
    Then the status code of the response is 200
#	And the response should contain
	
	