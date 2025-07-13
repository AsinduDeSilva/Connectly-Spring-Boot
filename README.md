# Connectly - Spring Boot Social Media App

A simple social media web application built using *Spring Boot*.

## Features

### 1. Landing, Registration & Authentication
- *Landing Page* shows the login form by default.
- *Registration*:
    - Form includes email, password, date of birth, first name, and last name.
    - Validated with Spring Boot validation annotations.
    - Passwords are hashed using *BCrypt*.
- *Login*:
    - Uses Spring Security for authentication.
    - Session state maintained.
    - Redirects to Home Feed on success.
    - Errors shown on failure.

### 2. Home Feed
- *Post Creation*:
    - Text-only posts with input validation.
- *Feed Display*:
    - Shows current user's posts with author, timestamp, and content.

### 3. Session Management
- Logout available on all authenticated pages.
- Session invalidated and user redirected to the Landing Page on logout.

### 4. Friend Management System
- *User Directory*:
    - Paginated list of all users (excluding current user).
- *Friend Requests*:
    - Send, accept, decline requests.
    - View incoming and outgoing requests.
- *Home Feed Integration*:
    - Posts from friends and user merged, sorted by newest first.

---

## Technical Requirements

### Database Configuration
- *Database*: PostgreSQL
- *Schema*: Auto-generated using JPA DDL

### Architecture & Design
- Follows *Spring MVC Architecture*
- *DTOs* used for data exchange
- Adheres to *OOP & SOLID principles*

### Frontend
- Built using *Thymeleaf*


---

## Contributors

- [Asindu de Silva](https://github.com/AsinduDeSilva)
- [Lakeesha Arunodi](https://github.com/ArunodiAAL)
- [Pathum Vidushan](https://github.com/pathumvidushan)
- [Kalana Nilwakka](https://github.com/KalanaNilwakka)
- [Sandeep Nilwakka](https://github.com/SandeepDiluksha)
- [Dewmi Samadhi](https://github.com/DewmiS)

---
