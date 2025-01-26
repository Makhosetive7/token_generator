# **Title** : Token Generating System

# **Author** : Makhosetive Sibanda

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Endpoints](#endpoints)
- [Functionality Details](#functionality-details)
- [Endpoint Details](#endpoint-details)

## Overview

This Spring Boot application generates user-specific tokens linked to individual accounts and validates their authenticity, expiration, and usage. The additional features of Token generating system include:

## Features

- **Token Generation and Validation** : Securely generates and manages tokens, ensuring their authenticity and checking expiration dates.

- **Token Transfer** : Enables users to transfer tokens between accounts, allowing seamless transactions and exchanges.

- **Token Donation** : Users can convert their tokens into monetary value to donate to causes such as Education Support, Food Programs, and Health Programs.

- **Local Vendor Purchases** : Allows users to use their tokens to purchase goods and services in selected stores, enhancing the tokens' utility in the local economy.

## Endpoints

| Method | Endpoint                          |
| ------ | --------------------------------- |
| GET    | `/api/users/allUsers`             |
| GET    | `/api/users/{id}`                 |
| GET    | `/api/tokens/`                    |
| GET    | `/api/tokens/{id}`                |
| POST   | `/api/tokens/generateToken/`      |
| POST   | `/api/users/register`             |
| POST   | `/api/tokens/TokenTransfer`       |
| POST   | `/api/tokens/createDonation`      |
| POST   | `api/tokens/localVendor/purchase` |
| DELETE | `/api/users/{id}`                 |
| DELETE | `/api/tokens/delete/{id}`         |

## Functionality Details

### Token Generation

- **User Registration**: Users must registered and authenticated to participate in the token generating system.
- **Token Creation**: Tokens are generated based on user actions or purchases, each linked to a specific user account.
- **Token Details**: Each token includes a unique ID, value, and expiration date.

### Token Validation

- **Authenticity Check**: Tokens are validated against a secure database to ensure they are legitimate.
- **Expiration Check**: Tokens are checked for expiration before any transaction.
- **Usage Verification and validation**: Ensures that tokens being used match the user's account.

### Token Transfer

- **Initiation**: Users can transfer tokens to other users by specifying the and sender and recipient’s account numbers.
- **Validation**: Both accounts are validated to prevent fraud.
- **Completion**: Upon successful validation, the tokens are transferred, and both accounts are updated.

### Token Donation

- **Conversion**: Users can convert tokens into monetary value.
- **Selection of Cause**: Users select from available donation causes (Education Support, Food Programs, Health Programs).
- **Transaction**: The donation is processed, and the user's token balance is updated.

### Local Vendor Purchases

- **Conversion**: Users can convert tokens into monetary value for use at local vendors.
- **Partner Integration**: Local vendors are integrated into the system, allowing users to make purchases (Zapalala, Doves, Choppies, Edgars).
- **Transaction**: Users can complete purchases at local vendors using their tokens.

### Error Handling

- **Invalid Tokens**: Invalid tokens are flagged, and the transaction is immediately stopped.
- **Insufficient Tokens**: Users are notified if they do not have enough tokens for a transaction.
- **Failed Transactions**: Failed transactions are logged, and users are notified.

### Logging and Auditing

- **Transaction Logs**: All token transactions are logged for security and auditing.
- **Audit Trails**: Maintains records of all transactions for transparency and accountability.

## Endpoint Details

## User endpoint details

### POST /api/users/register

- This /api/users/register endpoint is expected to let the user register a user account within the system.
- The implementation of the endpoint is as follows:

```json
 {
        "userName": "John",
        "lastName": "Doe",
        "password": "150376333",
        "email": "john@gmail.com",
        "phoneNumber": "123456789",
        "homeAddress": "123 Main St"
    },

```

- The expected response body is as follows:

```json
{
"id": 1,
"userName": "John",
"lastName": "Doe",
"email": "john@gmail.com",
"password": "150376333",
"accountNumber": "0718939103",
"phoneNumber": "123456789",
"homeAddress": "123 Main St",
"tokenBalance": null,
"role": null,
"transactionHistory": null,
"localVendorHistory": null,
"donationHistory": null
}
```

### GET /api/users/allUsers

- This /api/users endpoint returns a list of users registered with Token Generator application
- The expected response is a list of users

```json
  {
        "id": 1,
        "userName": "John",
        "lastName": "Doe",
        "accountNumber": "150376333",
        "phoneNumber": "123456789",
        "homeAddress": "123 Main St"
    },
    {
        "id": 2,
        "userName": "Makhosetive",
        "lastName": "Sibanda",
        "accountNumber": "315633594",
        "phoneNumber": "123456789",
        "homeAddress": "442 Main St"
    },
```

### GET /api/users/{id}

- This /api/users/{id} returns a user account by account identity number
- The expected response body is a JSON object containing the following:

```json
{
  "id": 15,
  "userName": "Imagine",
  "lastName": "Imagine",
  "accountNumber": "0292406856",
  "phoneNumber": "0713000001",
  "homeAddress": "1234 Elm Street"
}
```

### GET /api/users/account/{accountNumber}

- This /api/users/account/{accountNumber} returns a user account by account number
- The expected response body is a JSON object containing the following:

```json
{
  "id": 15,
  "userName": "Imagine",
  "lastName": "Imagine",
  "accountNumber": "0292406856",
  "phoneNumber": "0713000001",
  "homeAddress": "1234 Elm Street"
}
```

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
  "homeAddress": "0000 Main Street"
}
```

## Tokens Endpoints Details

### GET /api/tokens/

- This /api/tokens endpoint retrieves tokens that have been created in the last 30 days
- This endpoint returns response structured as a JSON object

```json
 {
        "accountNumber": 962475096,
        "amountPaid": 50000.0,
        "tokenGenerated": "SGIR4TM5H50DM8Q16GYL",
        "serialNumber": "0323431849",
        "kiloWatts": 70000.0,
        "createdAt": "2024-12-02T17:55:54.371897",
        "expiredAt": "2025-02-15T17:55:54.371897",
        "id": 12
    },
```

### POST /api/tokens/generateToken

- This /api/tokens/generateToken endpoint generates a new tokens for registered users

```json
{
  "accountNumber": "0347133011",
  "amountPaid": 2500,
  "serialNumber": 1234567898
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
  "accountNumber": 347133011,
  "amountPaid": 2500.0,
  "tokenGenerated": "LEIKIV4ZSOD2476SU2PM",
  "serialNumber": "0415404665",
  "kiloWatts": 3500.0,
  "createdAt": "2024-12-09T15:03:27.727753",
  "expiredAt": "2025-02-22T15:03:27.727753",
  "id": 15
}
```

### DELETE /api/tokens/{id}

- This endpoint /api/tokens/{id} deletes created tokens from the database

### POST /api/tokens/tokenTransfer

- This /api/tokens/TokenTransfer endpoint allows two different registered users to transfer tokens to each other
- The token transfer endpoint is implemented in the following example:

```json
{
  "senderAccountNumber": "0287205907",
  "receiverAccountNumber": "0611376174",
  "kilowatts": 100,
  "transferTokenId": "1234567890"
}
```

- The expected response body is a JSON object containing the following:

```json
{
  "receiverAccountNumber": {
    "accountNumber": 611376174,
    "amountPaid": 1007.0,
    "tokenGenerated": "O0LY9KJ72W3ZDOONBYPT",
    "serialNumber": "0945436946",
    "kiloWatts": 1509.8,
    "createdAt": "2024-12-10T01:38:01.579584",
    "expiredAt": "2025-02-23T01:38:01.579584",
    "id": 20
  },
  "senderAccountNumber": {
    "accountNumber": 287205907,
    "amountPaid": 1009.0,
    "tokenGenerated": "NQY8BF213K74HNGPCP1N",
    "serialNumber": "0536367267",
    "kiloWatts": 1312.6,
    "createdAt": "2024-12-10T01:38:41.15296",
    "expiredAt": "2025-02-23T01:38:41.15296",
    "id": 21
  },
  "kiloWatts": 100.0,
  "createdAt": null,
  "id": 1,
  "transferTokenId": 1234567890
}
```

### POST /api/tokens/createDonation

- This /api/tokens/createDonations endpoint allows users with extra tokens that they feel like they can convert to monetary value a the systems rate to convert and donate towards education support, food programs and towards health care.
- This endpoint is implemented in the following way:

```json
{
  "donatorsAccountNumber": "0611376174",
  "kiloWatts": 100.0,
  "donationType": "HEALTH_PROGRAMS"
}
```

- The expected response body should be a JSON object with the following:

```json
{
  "id": 1,
  "donationAccountNumber": {
    "accountNumber": 287205907,
    "amountPaid": 1009.0,
    "tokenGenerated": "NQY8BF213K74HNGPCP1N",
    "serialNumber": "0536367267",
    "kiloWatts": 1312.6,
    "createdAt": "2024-12-10T01:38:41.15296",
    "expiredAt": "2025-02-23T01:38:41.15296",
    "id": 21
  },
  "donatorsAccountNumber": {
    "accountNumber": 611376174,
    "amountPaid": 1007.0,
    "tokenGenerated": "O0LY9KJ72W3ZDOONBYPT",
    "serialNumber": "0945436946",
    "kiloWatts": 1409.8,
    "createdAt": "2024-12-10T01:38:01.579584",
    "expiredAt": "2025-02-23T01:38:01.579584",
    "id": 20
  },
  "kiloWatts": null,
  "amountDonated": 50.0,
  "convertedValue": null,
  "donationType": "HEALTH_PROGRAMS",
  "createdAt": "2024-12-11T02:17:39.4127519"
}
```

### POST api/tokens/localVendor/purchase

- This endpoint /api/tokens/localVendor/purchase allows regestered users with Tokens to convert them to monetary value and be able to purchase at groceries and appliances at selected stores
- The endpoint is implemented as follows:

```json
{
  "vendorAccountNumber": "287205907",
  "purchaseAccountNumber": "0611376174",
  "vendorTypeEnumerator": "ZAPALALA",
  "convertedValue": 45.5,
  "kiloWatts": 50,
  "purchaseAmount": 100
}
```

- The expected response is as follows:

```json
{
  "id": 1,
  "vendorAccountNumber": {
    "accountNumber": 287205907,
    "amountPaid": 1009.0,
    "tokenGenerated": "NQY8BF213K74HNGPCP1N",
    "serialNumber": "0536367267",
    "kiloWatts": 1262.6,
    "createdAt": "2024-12-10T01:38:41.15296",
    "expiredAt": "2025-02-23T01:38:41.15296",
    "id": 21
  },
  "purchaseAccountNumber": {
    "accountNumber": 611376174,
    "amountPaid": 1007.0,
    "tokenGenerated": "O0LY9KJ72W3ZDOONBYPT",
    "serialNumber": "0945436946",
    "kiloWatts": 1409.8,
    "createdAt": "2024-12-10T01:38:01.579584",
    "expiredAt": "2025-02-23T01:38:01.579584",
    "id": 20
  },
  "vendorTypeEnumerator": "ZAPALALA",
  "convertedValue": 22.75,
  "purchaseAmount": 100.0,
  "kiloWatts": null,
  "createdAt": "2024-12-11T09:21:11.7464155"
}
```
