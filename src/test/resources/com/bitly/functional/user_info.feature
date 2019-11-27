Feature: User Info endpoint

 Background: Existing user credentials
    Given valid user credentials are available

  Scenario: successful request to User Info
    When I make a request to the 'User Info' endpoint
    Then the status code of the response is 200
	And the response body should contain attribute 'status_code' with number value 200
	And the response body should contain attribute 'status_txt' with text value 'OK'
	And the response body should contain attribute 'data.full_name' with text value 'elseviertests'
	
	