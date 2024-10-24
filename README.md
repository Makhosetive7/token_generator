# Token Generating System
# Makhosetive Sibanda

## Overview
This Spring Boot application generates user-specific tokens linked to individual accounts and validates their authenticity, expiration, and usage status.
---
## Features
- **User Registration & Authentication**: Users can register.
- **User Account Management**: Users register their account details, including a unique Identity number.
- **Token Generation**: Users can purchase and generate tokens.
- **Token Validation**: The system verifies tokens during usage, ensuring they are valid for the specified user account.
- **Token Expiry**: Tokens have an expiration date and can only be used within a defined period.
- **Token history purchases**: This includes details like when tokens were generated, their expiration, and the amount of electricity they correspond to.
- **Token transfer**:  users to transfer tokens to other users, such as friends or family.
- **Token investing**:  users can borrow the system their tokens for a certain period to earn interest or additional tokens as rewards.
- **Dynamic Pricing**:  token prices adjust based number of purchases within a same month.
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
