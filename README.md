# Token Generating System

# Makhosetive Sibanda

## Overview

This Spring Boot application generates user-specific tokens linked to individual accounts and validates their authenticity, expiration, and usage status.

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

## Endpoint Details

## User endpoint details

### GET /api/users

- This /api/users endpoint returns a list of users registered with Token Generator application

### GET /api/users/{id}

- This /api/users/{id} returns a user account by account identity number

### GET /api/users/account/{accountNumber}

- This /api/users/account/{accountNumber} returns a user account by account number

### Delete /api/users/account/{accountNumber}

- This /api/users/account/{accountNumber} deletes a user account by account number

### POST /api/users/register

- This /api/users/register endpoint registers a new user into the Token Generator application system
- The implementation of this endpoint is as follows:

```json
{
   "userName": "Laurren",
   "lastName": "Madlodlo_Omuhle",
   "phoneNumber": "0713000000",
   "homeAddress": "0000 Main Street", 
}
```

## Tokens Endpoints Details

### GET /api/tokens/

- This /api/tokens endpoint retrieves tokens that have been created in the last 30 days

### POST /api/tokens/generateToken

- This /api/tokens/generateToken endpoint generates a new tokens for registered users

```json
{
    "accountNumber" : "0347133011",
    "amountPaid": 2500,
    "serielNumber": 1234567898
}
```

- The intended response to this endpoint is a JSON response containing the following fields: 

```json
{
    "accountNumber": 347133011,
    "amountPaid": 2500.0,
    "tokenGenerated": "LEIKIV4ZSOD2476SU2PM",
    "serialNumber": "0415404665",
    "kiloWatts": 3500.0,
    "createdAt": "2024-12-09T15:03:27.7277533",
    "expiredAt": "2025-02-22T15:03:27.7277533",
    "id": 15
}
```

### GET /api/tokens/{id}

- This /api/tokens/{id} endpoint returns available tokens by identity
- The endpoint intended response is as follows:

```json
{
    "accountNumber":347133011,
    "amountPaid":2500.0,
    "tokenGenerated":"LEIKIV4ZSOD2476SU2PM",
    "serialNumber":"0415404665",
    "kiloWatts":3500.0,
    "createdAt":"2024-12-09T15:03:27.727753",
    "expiredAt":"2025-02-22T15:03:27.727753",
    "id":15
}
```

### DELETE /api/tokens/{id}

- This endpoint /api/tokens/{id} deletes created tokens from the database

### POST /api/tokens/TokenTransfer

- This /api/tokens/TokenTransfer endpoint allows two different registered users to transfer tokens to each other
- The token transfer endpoint is implemented in the following example:

```json
{
    "senderAccountNumber" : "0241682637",
    "receiverAccountNumber" : "0761345112",
    "kilowatts" : 120.5
}
```
