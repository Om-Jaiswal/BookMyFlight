# BookMyFlight
BookMyFlight: FMS(Flight Management System) using Angular + Spring Boot + MongoDB

## Description
BookMyFlight is a Java-based solution that allows users to quickly book flights and manage booking information, and cancellations easily. It consolidates data from different airline carriers and thus provides all the necessary details and rates. In addition, administrators of flight data can also quickly view, create, and update any information about flights, bookings, routes, and schedules.

#### Microservice Architecture Diagram
![Architecture Diagram](/images/architecture-diagram.png)

## Technologies 
#### Frontend Technologies
1. HTML 5
2. CSS 3
3. Bootstrap
4. Typescript
5. Angular
6. Jasmine & Karma (Testing)
#### Backend Technologies:
1. Java
2. Spring Boot
4. REST API
5. Swagger
6. RabbitMQ
7. JUnit Testing (Mockito)
#### Developer Tools
1. PMD
2. Sonar Lint
3. Jacoco
4. Devtools
#### Database Management
1. MongoDB

## Features
BookMyFlight can be accessed by two categories of users:

#### Customers
1. Customers can create a new account in order to log in.
2. Customers can search available flights.
3. Customers can make a booking, view current bookings, and cancel a booking, if needed. 
4. Customers can see airport details for the airports associated with their flight(s).

#### Administrators
1. Administrators can view all details for flights, schedules, and routes.  
2. Administrators can add, modify, and cancel flights, schedules, and routes.

## Developers

Note: Make sure server is running before calling any request from client to server.

#### Access the Client App
1. Install Angular CLI if not already installed in your system.
2. Clone this project. 
3. Run `npm install` in the `cmd` line in the directory of the project. 
4. Then, run `ng serve`.
5. Navigate to http://localhost:4200/.

#### Running Unit Tests
1. Run `ng test` to execute the unit tests via Karma.

#### Access the Server App
1. Install Spring Tool Suite if not already installed in your system.
2. Clone this project. 
3. Run all the microservices as Spring Boot App.
