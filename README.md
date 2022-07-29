# Equal Experts take home assignment

## Description
This project contains two classes that implement the shopping cart requirement in the problem
statement, namely Product and ShoppingCart.

The problem statement document can be found in the project root folder.

## Testing
There are unit test classes for the Product and the ShoppingCart.

There is also an integration test that fetches product pricing data from the urls provided in the problem
statement.  The data is fetched using the HttpClient. The integration test runs three scenarios:

* Add one product to the shopping cart
* Add multiple products to the shopping cart
* Add multiple products multiple times to the shopping cart

## Technology Stack
* Language: Java
* Build Tool: Maven
* Testing: JUnit5, Hamcrest

## Prerequisites
* Java 17
* Maven 3.6.3

## Run instructions
The project uses Maven to compile, package and execute tests.

### To compile the code
mvn clean compile

### To execute the tests
mvn clean test

### To package the code into a jar file
mvn clean package