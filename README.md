# GradingSystem

This project is an API built using Java, Java Spring, Flyway Migrations, PostgresSQL as the database, and Spring Security and JWT for authentication control.

# API Endpoints

 - POST /auth/login - Login into the App
  
 - POST /auth/register - Register a new user into the App

 - POST /subjects - Register a new subject (TEACHER/ADMIN access required).
   
 - POST /grades - Register a new grade (TEACHER/ADMIN access required).

 - GET /grades/user/{id}- Retrieve a subject by user id. (all authenticated users)

 - GET /grades/average/{studentId}/{subjectId} - Calculates the average of a student's grades (all authenticated users)

 - GET /grades/approved/{studentId}/{subjectId} - Check if the student is approved (all authenticated users)

 - GET /subjects - Retrieve a list of all subjects. (all authenticated users)

 - PUT /subjects/{id} - Update a subject by id. (all authenticated users)

 - DELETE /subjects/{id} - Delete a subject by id. (all authenticated users)


# Authentication

The API uses Spring Security for authentication control. The following roles are available:

- STUDENT -> Standard user role for logged-in users.
- TEACHER -> Admin role for managing the system (registering new subjects and grades).

To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

# Database

The project utilizes PostgresSQL as the database. The necessary database migrations are managed using Flyway.

 
