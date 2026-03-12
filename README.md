# 📝 Blog Platform API

## 📌 Description

A RESTful API built with **Spring Boot** for managing a simple blogging platform.

The system allows users to register and authenticate using **JWT-based authentication**, after which they can interact with blog content depending on their role.

The platform supports three main roles:

- **ADMIN** – Full system access, including managing users, categories, tags, and posts.
- **AUTHOR** – Can create, update, publish, and manage their own blog posts.
- **USER** – Can browse and read published blog posts.

The application provides full CRUD operations for **posts, categories, tags, and users**, while enforcing role-based access control using **Spring Security**.

Important business logic is implemented, such as:

- Draft and publish workflow for posts
- Automatic post reading-time calculation
- Role-based authorization for different operations
- Secure authentication using **JWT tokens**
- Validation and consistent error handling through **Global Exception Handling**

The project follows a **clean layered architecture** and is designed with maintainability, scalability, and real-world backend practices in mind.

---

## 📋 Table of Contents

- [📌 Description](#-description)
- [🛠 Tech Stack](#-tech-stack)
- [🚀 Features](#-features)
- [📡 API Endpoints](#-api-endpoints)
  - [🔐 Authentication](#-authentication)
  - [👤 Users](#-users)
  - [📝 Posts](#-posts)
  - [🏷 Tags](#-tags)
  - [📂 Categories](#-categories)
- [📸 Screenshots](#-screenshots)
- [🎥 Demo Video](#-demo-video)
- [⚙️ Installation Guide](#-installation-guide)
  - [Prerequisites](#1️⃣-prerequisites)
  - [Clone the Repository](#2️⃣-clone-the-repository)
  - [Database Configuration](#3️⃣-database-configuration)
- [🗄 Database Schema](#database-schema)
- [🗺 Database ERD](#database-schema-erd)

---

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 3.5**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **JWT Authentication (jjwt)**
- **MySQL**
- **MapStruct**
- **Lombok**
- **Spring Validation**
---

## 🚀 Features

- User registration and authentication using **JWT**
- Role-Based Access Control using **Spring Security**
- Support for multiple roles:
  - **ADMIN** – Full system access
  - **AUTHOR** – Manage and publish their own posts
  - **USER** – Browse and read posts
- Create, update, and manage blog posts
- Draft → Publish workflow for posts
- Automatic **reading time calculation** based on post content
- Categorize posts using **Categories**
- Organize posts using **Tags**
- Pagination and sorting support for retrieving posts
- Search posts by title
- Retrieve draft posts for the currently authenticated author
- Secure endpoints using **JWT Authentication**
- Global exception handling for consistent API error responses
- DTO-based architecture with **MapStruct**
- Validation using **Spring Validation**
- Environment-based configuration using **Spring Profiles (dev, staging, prod)**
- Interactive API documentation using **Swagger UI**

---

## 📡 API Endpoints

### 🔐 Authentication

| Method | Endpoint | Description |
|------|------|------|
| POST | /api/v1/auth/register | Register new user |
| POST | /api/v1/auth/login | Authenticate user and receive JWT token |
| PATCH | /api/v1/auth/reset/{id} | Reset user password |

---

### 👤 Users

| Method | Endpoint | Description |
|------|------|------|
| PUT | /api/v1/users/{id} | Update user |
| GET | /api/v1/users/{id} | Get user by ID |
| GET | /api/v1/users/search?email={email} | Find user by email |
| GET | /api/v1/users | Get all users |
| DELETE | /api/v1/users/{id} | Delete user |

---

### 📝 Posts

| Method | Endpoint | Description |
|------|------|------|
| POST | /api/v1/posts | Create new post (Draft) |
| PUT | /api/v1/posts/{id} | Update post |
| PUT | /api/v1/posts/publish/{id} | Publish post |
| GET | /api/v1/posts/{id} | Get post by ID |
| GET | /api/v1/posts/search?title={title} | Search post by title |
| GET | /api/v1/posts | Get all posts (pagination & sorting supported) |
| GET | /api/v1/posts/drafts | Get draft posts for current author |
| DELETE | /api/v1/posts/{id} | Delete post |

---

### 🏷 Tags

| Method | Endpoint | Description |
|------|------|------|
| POST | /api/v1/tags | Create tag |
| PUT | /api/v1/tags/{id} | Update tag |
| GET | /api/v1/tags/{id} | Get tag by ID |
| GET | /api/v1/tags/search?name={name} | Search tag by name |
| GET | /api/v1/tags | Get all tags |
| DELETE | /api/v1/tags/{id} | Delete tag |

---

### 📂 Categories

| Method | Endpoint | Description |
|------|------|------|
| POST | /api/v1/categories | Create category |
| PUT | /api/v1/categories/{id} | Update category |
| GET | /api/v1/categories/{id} | Get category by ID |
| GET | /api/v1/categories/search?name={name} | Find category by name |
| GET | /api/v1/categories/all | Get all categories with post count |
| DELETE | /api/v1/categories/{id} | Delete category |

---

## 📸 Screenshots

All project screenshots are available in the folder below:

📂 [View Screenshots](./screenshots)

You can find screenshots demonstrating:

- User registration and authentication
- Creating and publishing blog posts
- Managing categories and tags
- Swagger API documentation
- JWT secured endpoints

---

## 🎥 Demo Video

A full walkthrough of the project and its main features is available here:

▶️ **[Watch the Demo on YouTube](https://youtu.be/olk8zJg9_uM?si=jwWYmVVIykPL5DU4)**

The demo covers:

- User registration and login
- JWT authentication workflow
- Creating draft posts and publishing them
- Managing categories and tags
- Access control based on user roles (Admin, Author, User)
- Exploring the API using Swagger UI
  
---

## ⚙️ Installation Guide

Follow the steps below to run the project locally.

---

### 1️⃣ Prerequisites

Make sure you have the following installed:

- Java 21
- Maven
- MySQL
- Git

---

### 2️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/blog-platform.git
cd blog-platform
```

---

### 3️⃣ Database Configuration

This project uses **Spring Profiles**:

- `dev`
- `staging`
- `prod`

All profiles require environment variables for database connection.

---

## 🔹 Required Environment Variables

Create the following environment variables:

```
MYSQL_URL
USER_NAME
PASSWORD
```

Example values:

```
MYSQL_URL=jdbc:mysql://localhost:3306/blog_platform?createDatabaseIfNotExist=true
USER_NAME=root
PASSWORD=your_password_here
```

### Example (Windows PowerShell)

```powershell
setx MYSQL_URL "jdbc:mysql://localhost:3306/blog_platform?createDatabaseIfNotExist=true"
setx USER_NAME "root"
setx PASSWORD "your_password_here"
```

### Example (Linux / Mac)

```bash
export MYSQL_URL=jdbc:mysql://localhost:3306/blog_platform?createDatabaseIfNotExist=true
export USER_NAME=root
export PASSWORD=your_password_here
```

Restart your terminal after setting the variables.

---

### 4️⃣ Build the Project

```bash
mvn clean install
```

---

### 5️⃣ Run the Application (dev profile)

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

The application will start on:

```
http://localhost:8080
```

---

### 6️⃣ Access Swagger UI

After the application starts, open:

```
http://localhost:8080/swagger-ui/index.html
```

You can explore and test all API endpoints from there.

---

# 🗄 Database Schema

## Users

| Column | Type | Constraints |
|------|------|------|
| id | BIGINT | PK, Auto Increment |
| username | VARCHAR | NOT NULL |
| email | VARCHAR | UNIQUE, NOT NULL |
| password | VARCHAR | NOT NULL |
| role | VARCHAR | ENUM (ROLE_ADMIN, ROLE_AUTHOR, ROLE_USER) |
| created_at | DATETIME | Created Timestamp |

---

## Categories

| Column | Type | Constraints |
|------|------|------|
| id | BIGINT | PK, Auto Increment |
| name | VARCHAR | UNIQUE, NOT NULL |

---

## Tags

| Column | Type | Constraints |
|------|------|------|
| id | BIGINT | PK, Auto Increment |
| name | VARCHAR | UNIQUE, NOT NULL |

---

## Posts

| Column | Type | Constraints |
|------|------|------|
| id | BIGINT | PK, Auto Increment |
| title | VARCHAR | NOT NULL |
| content | TEXT | |
| reading_time | INT | Auto Calculated |
| status | VARCHAR | ENUM (DRAFT, PUBLISHED) |
| created_at | DATETIME | Created Timestamp |
| updated_at | DATETIME | Last Updated Timestamp |
| published_at | DATETIME | |
| user_id | BIGINT | FK → Users(id) |
| category_id | BIGINT | FK → Categories(id) |

---

## Posts_Tags (Join Table)

| Column | Type | Constraints |
|------|------|------|
| post_id | BIGINT | FK → Posts(id) |
| tag_id | BIGINT | FK → Tags(id) |

---

# 🗺 Database Schema (ERD)

![ERD](screenshots/schema.png)
