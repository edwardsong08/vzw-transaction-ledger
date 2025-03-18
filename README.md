# VZW Transaction Ledger

## Overview
This project is a proof-of-concept application that simulates a blockchain-based transaction ledger. It demonstrates my ability to rapidly learn and integrate multiple enterprise technologies using Java full-stack development. The application is built with Spring Boot and Thymeleaf, and it leverages a PostgreSQL database. For local development, PostgreSQL runs in a Docker container, while in production the application connects to an AWS RDS PostgreSQL instance. The project also features a blockchain simulation module that maintains an immutable audit trail for all transactions. Additionally, the UI supports both English and Korean, and the project integrates both Spring Data JPA and MyBatis for flexible and powerful data access.

## Features
- **Java Full-Stack Development:** REST API and server-rendered UI using Spring Boot and Thymeleaf.
- **Database Integration:** 
  - Local: PostgreSQL running in a Docker container.
  - Production: AWS RDS PostgreSQL instance.
- **ORM & Custom Queries:** Combines Spring Data JPA with MyBatis for handling standard operations and complex queries.
- **Blockchain Simulation:** An audit trail that records and validates the creation, update, and deletion of transactions.
- **Input Validation & Error Handling:** Robust validations and informative error messages.
- **Bilingual User Interface:** English and Korean language support.
- **Cloud Deployment:** Deployed on AWS using Docker containers on EC2 and a managed PostgreSQL database on RDS.
- **CI/CD Pipeline:** Automated build, test, and deployment processes using GitHub Actions.

## Technology Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.4.3
- **Build Tool:** Gradle
- **User Interface:** Thymeleaf
- **Database:** PostgreSQL  
  - Local Development: Docker container  
  - Production: AWS RDS
- **Data Access:** Spring Data JPA, MyBatis
- **Validation:** Spring Boot Starter Validation
- **Testing:** JUnit 5, AssertJ, Spring Boot Starter Test
- **Containerization:** Docker
- **Cloud Platform:** AWS (EC2 for app deployment, RDS for database)
- **CI/CD Pipeline:** GitHub Actions

## Testing

**Unit/Integration Tests:**
- Comprehensive tests covering the blockchain simulation, repository operations, and service logic.
- Automated tests are executed via Gradle and integrated into the CI/CD pipeline.

**Manual API Testing:**
- Use Postman to verify API endpoints for Users, Transactions, and the blockchain ledger.
- Confirm proper error handling, data validation, and consistency of responses.

**Manual UI Testing:**
- Validate that the front-end functions correctly and displays data as expected.
- Cross-reference UI data with raw JSON endpoints to ensure accuracy.

## Future Enhancements

- **Blockchain Simulation:**  
  Extend the simulation module into a fully functional blockchain with enhanced security features and consensus mechanisms.
- **User Interface Improvements:**  
  Enhance the UI using modern frameworks, and add features such as client-side sorting, pagination, and interactive visualizations.
- **Automated Deployment:**  
  Further refine and integrate AWS deployment scripts into a robust CI/CD pipeline to automate testing, building, and deployment.
- **Testing Infrastructure:**  
  Incorporate an H2 in-memory database for local testing to simplify development, reducing reliance on Docker-based PostgreSQL for tests.

**Note:**  
This project demonstrates my ability to quickly learn and integrate multiple enterprise technologies. While the current UI is intentionally kept simple to focus on core functionality and architectural proof of concept, it provides a solid foundation for further enhancements and production-level optimizations.
