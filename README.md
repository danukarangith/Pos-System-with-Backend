# Post System Backend 

## Overview

This repository contains the backend API for a Post System, developed using Java. The API manages operations related to posts, customers, items, and orders, providing a comprehensive solution for CRUD operations on these entities. The system integrates with MySQL for data storage and utilizes JSON for data interchange. Authentication and authorization are handled through Azure Active Directory (AAD) using Java AAD.

## Features

 - **Customer Management**: Create, update, retrieve, and delete customer profiles.
- **Item Management**: Manage the inventory by adding, updating, retrieving, and removing items.
- **Order Management**: Handle customer orders, including creation, status updates, and order retrieval.
- **Post Management**: Perform CRUD operations on posts, including filtering and pagination.

## Technologies Used

- **Java**: The primary programming language for developing the API.
- **MySQL**: Relational database management system for storing data.
- **JSON**: Data format used for request and response payloads.
 

## Getting Started

### Prerequisites

- **Java** (JDK 11 or higher)
- **MySQL** (v8.x or higher)
- **Maven** (for dependency management)
 

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/danukarangith/post-system-backend.git
    ```
2. Navigate to the project directory:
    ```bash
    cd post-system-backend
    ```
3. Configure your MySQL database:
    ```sql
    CREATE DATABASE post_system;
    ```
 
    ```

4. Build the project with Maven:
    ```bash
    mvn clean install
    ```

5. Run the application:
 
    ```

### Running Tests

 test with postman : https://documenter.getpostman.com/view/36300872/2sA3s1psGt
