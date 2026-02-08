# ✍️ Advanced Blog Platform API

A feature-rich RESTful Backend service for a blogging platform, featuring secure user authentication, role-based access control, and automated content management.

## 🚀 Overview
This API provides a complete backend for a multi-user blog. It supports different user roles (**ADMIN**, **AUTHOR**, **USER**), allowing for a structured content lifecycle from draft creation to public publishing, complete with category and tag management.

## 🛠️ Tech Stack
* **Java 21**
* **Spring Boot 3.5.x**
* **Spring Security & JWT** (Json Web Token)
* **Spring Data JPA** (MySQL)
* **MapStruct & Lombok**
* **Hibernate Auditing** (Tracking created/updated dates)
* **Swagger/OpenAPI 3**

## 🔐 Security Features
* **JWT Authentication:** Secure stateless login system.
* **RBAC (Role-Based Access Control):** * `ADMIN`: Full control over users, categories, and tags.
    * `AUTHOR`: Can create, update, and publish their own posts.
    * `USER`: Can read public posts.
* **Method Level Security:** Using `@PreAuthorize` to protect sensitive endpoints.

## ✨ Key Business Logic
* **Reading Time Calculator:** Automatically calculates the estimated reading time of a post based on word count before saving to the database.
* **Post Lifecycle:** Supports `DRAFT` and `PUBLISHED` statuses.
* **Dynamic Search:** Search posts by title and categories by name.
* **User Management:** Secure registration and password reset functionality.

## 📊 Database Architecture
The system utilizes a relational schema:
* **Users <1---M> Posts** (One user can write many posts).
* **Categories <1---M> Posts** (Each post belongs to one category).
* **Posts <M---N> Tags** (Many-to-many relationship between posts and tags).



## 📖 API Documentation
Explore and test the API endpoints via Swagger UI:
`http://localhost:8080/swagger-ui/index.html`

## 🚦 Getting Started

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/abdelrahman8284-sudo/blog-platform
2. Update application.properties with your MySQL credentials.
3. Build and Run:
   ```bash
   mvn spring-boot:run
### 📝 Future Enhancements
* Implementing a Comment System for posts.
* Adding Image Upload support for post covers (AWS S3).
* Implementing Email Verification for new users.
