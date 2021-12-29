Demo application for calculating reward points.

##Run instruction:

1. Prepare environment
    - install java 11 or higher
    - install maven
    - download project

2. Run from command line
    - mvn clean install (build application with tests)
    - mvn spring-boot:run (starts application on http://localhost:8080)

3. Application could be also run by intellij.

##How to test application

Swagger with endpoints is available on http://localhost:8080/swagger-ui/ when application is running.

There are users with id 1, 2 and 3 saved in database.

There are available products with id 4 (76 available quantity) and id 6 (18 available quantity).

There are not available product with id 5.

All operations can be done in swagger.

1. Create mew orders by order-controller.
2. Get user points by reward-controller.
