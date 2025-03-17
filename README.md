# VZW Transaction Ledger

## Overview
This project is a proof-of-concept application that simulates a blockchain-based transaction ledger. It demonstrates Java full-stack development using Spring Boot, integrates a PostgreSQL database (via Docker), and features a simple blockchain simulation module with bilingual support (English/Korean) using Thymeleaf.

## Features
- **Java Full-Stack Development:** REST API with Spring Boot.
- **Database Integration:** PostgreSQL configured via Docker.
- **ORM Integration:** Combines Spring Data JPA and MyBatis.
- **Blockchain Simulation:** Basic module to simulate blockchain transactions.
- **Bilingual UI:** English and Korean language support.
- **Deployment Automation:** Shell scripts and AWS deployment concepts.

## Technology Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.4.3
- **Build Tool:** Gradle
- **UI:** Thymeleaf
- **Database:** PostgreSQL (via Docker)
- **Libraries:** Spring Data JPA, MyBatis

## Setup and Installation
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/vzw-transaction-ledger.git
2. Navigate to the project directory:
   ```bash
   cd vzw-transactions-ledger
3. Install Prerequisites:
    Java 17: Ensure Java17 is installed on your system. Verify with the following:
      ```bash
      java -version
      ``` 
      Docker Desktop:
      Install Docker Desktop and make sure it is running.    
4. Run PostgreSQL in Docker: Start a PostgreSQL container by executing:
      ```bash
      docker run --name postgres -e POSTGRES_USER=vzwuser -e POSTGRES_PASSWORD=vzwpass -e POSTGRES_DB=vzwledger -p 5432:5432 -d postgres
      ```
      This command sets up a PostgreSQL database with:  
        Username: vzwuser  
        Password: vzwpass  
        Database Name: vzwledger
5.  Configure the Application: Update the src/main/resources/application.properties file with the following database configuration:  
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/vzwledger
    spring.datasource.username=vzwuser
    spring.datasource.password=vzwpass
    spring.datasource.driver-class-name=org.postgresql.Driver

    ```
6.  Build and Run the Application: If you're using Gradle, run:  
    ```
    ./gradlew bootRun
    ```
    This command compiles and starts your Spring Boot application.
    
7.  Access the Application: Open your web browser and navigate to http://localhost:8080 to view your running application.

## Testing  
Run the tests using:
``` bash
./gradlew test
```
1. /test for Blockchain.java, UserRepository.java and UserSummaryService.java
2. manually tested each endpoint API with POSTMAN
3. manually tested all functions through front end and cross referenced with front end raw JSON data for Users, Transactions, and Blockchain ledger which records new, edited, and deleted transactions.

## Future Enhancements?  
1. Extend the blockchain simulation module.
2. Improve the UI and add more business logic.
3. Integrate AWS deployment scripts for a production environment.
   
