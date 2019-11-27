# bitly-api-test

The goal of this project is to demonstrate a test approach for API test automation.

# Automated Test Suite

These tests are written in Java using 'REST-assured' and 'Cucumber' as a testing framework.

## Scope
Scope has been limited to the following endpoints: 
- /v3/user/info
- /v3/user/link_history
- /v3/shorten

Coverage is limited to basic happy path requests and some negative scenarios. In normal circumstances each parameter should be tested with valid and invalid values according to boundary testing and partition equivalence technique (including empty, null, out of range values, etc).   

Additionally, the core business value of the application is not being tested. In these examples the focus is only in the test tool, notice that currently none of the test cases is actually checking if a short url is actually redirecting to the correct long url (core functionality of the Bitly application).

# Test Execution

## Preconditions

The requirements to run the automated tests are:
- Maven 3.6+
- Java 8+

### Maven project

This is a maven project. If Java and Maven are correctly configured in the host system, the test suite can be executed via command line. 
```
    $mvn clean test
```
This will run all the tests according to the scenarios available in the feature files in src/test/resources/com/bitly/functional.

### Test reports
Results will be available in the /target/cucumber/cucumber-html-reports/overview-failures.html after execution is completed. 

Current console log level is high and all the http requests and responses (with body) can be seen in console.

### Structure

#### Feature files
The scenarios are distributed across the different .feature files in an attempt to group by endpoint. However, the access_token.feature file contains a set of negative test cases hitting multiple endpoints to demonstrate the use of Scenario Outline and data table.
The user_link_history.feature contains a scenario demonstrating a more complex test hitting more than 1 endpoint.
#### Step definition classes
A BaseSteps class with common methods and constants can be extended by other step definitions classes.
#### Utils classes
The classes under the "utils" package are auxiliary classes with reusable code.
#### Properties file
The "datafile.properties" file in /resources/com/bitly/data has several parameters used during test execution. This prevents values from being hardcoded in the test classes thus allowing for easier code maintenance.
 
## Findings
There is a noticeable inconsistency in the responses coming from different endpoints when the invalid or missing access token request is made. The data table with expected behaviors in the "access_token.feature" file is currently matching the implementation for automation demonstration purposes but in normal circumnstances this should be further validated or bug report should be filed.
 