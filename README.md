# House Building API

## Description

This API provide.

## Features

- User login and registration
- Check account balance
- Transfer money between accounts
- Bill payments
- Mobile phone top-up
- Transaction history
- Notifications
- Back-office management

## Technologies Used

- Java 17
- Spring Boot 3.0.5
- Spring Data JPA
- PostgreSQL 14
- Maven

## Prerequisites

- Java 17 or higher installed on your machine
- Docker Desktop or Docker installed on your machine

## Installation

1. Clone the project from this GitHub repo

   ```git clone ```

2. Open the project in your preferred code editor

3. Copy file ```.env.exemple``` to ```.env```.

4. Edit the properties to match your environment and your preferences

5. Ensure Docker is running and launch the docker-compose.yml file to ensure you have all the infrastructure component on your machine
   ```docker compose up -d```

6. Copy file ```application.properties.exemple``` to ```àpplication.properties```.

7. Edit it with the properties corresponding to your environment. The current properties mostly match and environment build with the docker-compose.yml.

8. Compile and run the application using Maven:

   ```mvn clean install```

   ```mvn spring-boot:run```

9. Access the application via your web browser:
   http://localhost:8080

10. To obtain a token, login to the ```/online-banking/v1/authenticate``` endpoint for bank customers
    or ```/back-office/v1/authenticate```
    for back-office agents.

## Documentation

The API documentation is available via [Redoc](https://redocly.com/redoc/) and OpenAPI specification :

http://localhost:8080/api-docs.html

Feel free to contact [Quantech](https://quantech.solutions) or consult ```HELP.md```  if you have any questions or
comments about this
project.