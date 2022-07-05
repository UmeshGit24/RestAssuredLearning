# RestAssured Framework Learning

### Purpose: To Build a Automation framewok using Rest Assured to perform End to End Testing  of API at https://restful-booker.herokuapp.com/

## Tech Stack Used in Automation Framework:-

Programming Language:-Java

Automation Framework:-Rest Assured

Test Runner:-TestNg

Build Management:-Maven


# Project Structure
pom.xml:-Contains different dependency used in Project

testNg.xml:-Contains script to run @Test

src/test/java/com Folder consists:-

data:- Contains different POJO class and their Builder class

listener:-Contains a Listener class 

restfulBookerTest:-Contains E2E test 


# Scenario Coverage:-
I have Covered POST,GET,PUT,PATCH and DELETE request

# Dependency Used
Lombok is used for generating getter and Setter for PoJO class

Hamcrest Matchers are used for assertions.

Listener to capture event on Test Pass and Failure

Rest API on https://restful-booker.herokuapp.com/ is used for testing.




