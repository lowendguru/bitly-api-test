# bitly-api-test

The goal of this project is to demonstrate a test approach for API test automation.

# Automated Test Suite

These tests are written in Java using 'REST-assured' and 'Cucumber' as a testing framework.


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
This will run all the tests according to the scenarios found in the feature files in src/test/resources/com/bitly/functional.

### Test reports
Results will be available in the /target/cucumber/cucumber-html-reports folder after execution is completed. 

### Structure

#### Feature files
The scenarios are distributed across the different .feature files in an attempt to group by endpoint. However, the access_token.feature file contains a set of negative test cases hitting multiple endpoints to demonstrate the use of Scenario Outline and data table.


#### Step definition classes
A BaseSteps class with common methods and constants can be extended by other step definitions classes.
#### Utils classes
The classes under the "utils" package are auxiliary classes with reusable code.
#### Properties file
The "datafile.properties" file in /resources/com/bitly/data has several parameters used during test execution. This prevents values from being hardcoded in the test classes thus allowing for easier code maintenance.
 
### Findings

 