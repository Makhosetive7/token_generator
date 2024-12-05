# Token Generating System
# Makhosetive Sibanda

## Overview
This Spring Boot application generates user-specific tokens linked to individual accounts and validates their authenticity, expiration, and usage status.
---
## Features
- **User Registration & Authentication**: Users can register.
- **Token Generation and validation**: Users can purchase and generate tokens.The system verifies tokens during usage, ensuring they are valid for the specified user account.
- **Token Expiry**: Tokens have an expiration date and can only be used within a defined period.
- **Token history purchases**: This includes details like when tokens were generated, their expiration, and the amount of electricity they correspond to.
- **Token transfer**:  users to transfer tokens to other users, such as friends or family.
- **Charity and Donations**: Add an option for users to donate tokens to charitable causes or transfer their tokens to a shared pool that contributes to social good projects.
- **Local Vendor Support**: Partner with local vendors to accept tokens as a form of payment. This can stimulate the local economy and provide users with more options for using their tokens.

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
