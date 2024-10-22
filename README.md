# Token Generating System
# Makhosetive Sibanda

## Overview
This project is a Spring Boot-based application that generates tokens for users. Each token is linked to a 
specific User Account and can only be used for that account it was issued to. The system validates tokens, ensuring they 
are valid for the correct user account and haven't expired or been used already.
The main goal of this project is to explore **Spring Boot** and **microservices architecture**, with additional 
features like user authentication, validation, and token lifecycle management.
---
## Features
- **User Registration & Authentication**: Users can register and log in to the system.
- **User Account Management**: Users register their account details, including a unique Identity number.
- **Token Generation**: Users can purchase and generate tokens.
- **Token Validation**: The system verifies tokens during usage, ensuring they are valid for the specified user account.
- **Token Expiry**: Tokens have an expiration date and can only be used within a defined period.
- **Admin Portal**: Admins can manage users accounts, and tokens.
- **User Dashboard**: Users can view their token history and status.
- **Email Notifications**: Notifications for token expiry and successful usage.
- **Security**: Role-based authentication for users and admins.

## Endpoints

| Method    | Endpoint                          | Description                           |
|-----------|-----------------------------------|---------------------------------------|
| GET       | `/api/tokens/`                    | Retrieve all tokens.                  |
| GET       | `/api/tokens/{id}`                | Get a token by ID.                    |
| POST      | `/api/tokens/generateToken/`      | Create a new token.                   |
| DELETE    | `/api/tokens/delete/{id}`         | Delete a token by ID.                 |
| GET       | '/api/users/                      | Retrieve all users                    |
| GET       | `/api/users/{id}`                 | Retrieve user details by ID.          |
| POST      | `/api/users/register`             | Register a new user.                  |
| DELETE    | `/api/users/{id}`                 | Delete user details by ID.            |

## Tech Stack
- **Java**: Core language.
- **Spring Boot**: Framework for building the REST API and services.
 - Spring Web: For building REST
 - Spring Security: For user authentication and authorization.
 - Spring Data JPA: For database interactions.
- **Database**: MySQL/PostgreSQL for storing users, accounts, and tokens.
- **Docker**: Containerization for deployment.
- **JUnit & Mockito**: Unit testing.
- **ReactJs(Optional)**: For a web-based user interface (if needed).
- **Email Service (Optional)**: For sending notifications to users.
---
## Setup Instructions
### 1. Prerequisites
Before you start, ensure you have the following installed:
- **Java 17** (LTS)
- **Maven** (for dependency management)
- **MySQL** (for the database)
- **Docker** (for containerization, optional)
### 2. Clone the Repository
```bash
git clone https://github.com/your-username/token_generator.git
cd token_generator
