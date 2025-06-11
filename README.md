# Onneshon Blog API

A comprehensive RESTful API backend for a blogging platform, built with Spring Boot. This project powers the Onneshon Blog, supporting user authentication, blog management, categories, comments, and more. The UI is available as a separate React application: [Onneshon React UI](https://github.com/ShubratoDn/onneshon-react).

---

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [Setup & Installation](#setup--installation)
- [Environment Configuration](#environment-configuration)
- [API Endpoints](#api-endpoints)
- [Running the Application](#running-the-application)
- [Connecting the UI](#connecting-the-ui)
- [Contributing](#contributing)
- [License](#license)

---

## Features
- User registration, authentication (JWT), and role-based authorization
- Blog CRUD (Create, Read, Update, Delete)
- Category management
- Commenting system
- File upload for user avatars and blog images
- Pagination, sorting, and search for blogs
- Exception handling and validation
- API documentation with Swagger/OpenAPI

## Tech Stack
- **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Spring Security, JWT
- **Database:** MySQL/MariaDB
- **API Docs:** Springdoc OpenAPI/Swagger
- **Frontend:** [React (separate repo)](https://github.com/ShubratoDn/onneshon-react)

## Project Structure
```
src/main/java/com/onneshon/blog/
├── controllers/      # REST controllers (User, Blog, Category, Auth, Comment)
├── entities/         # JPA entities (User, Blog, Category, Comment, Role)
├── repositories/     # Spring Data JPA repositories
├── services/         # Service interfaces
├── servicesImple/    # Service implementations
├── payloads/         # DTOs and API response models
├── exceptions/       # Custom exceptions and handlers
├── helpers/          # Utility classes (file upload, validation)
├── configs/          # Security, JWT, and CORS configuration
└── OnneshonBlogApisApplication.java
```

## Database Schema
The main tables are:
- `users` (id, name, email, password, about, image)
- `roles` (id, role)
- `users_roles` (user_id, roles_id)
- `blogs` (id, blog_title, blog_content, blog_image, added_date, category_id, user_id)
- `categories` (category_id, category_title, category_description)
- `comments` (comment_id, content, blog_id, user_id)

See [`onneshon.sql`](./onneshon.sql) for the full schema and sample data.

## Setup & Installation
### Prerequisites
- Java 17+
- Maven
- MySQL or MariaDB

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/onneshon-blog-api.git
cd onneshon-blog-api
```

### 2. Database Setup
- Create a database (e.g., `onneshon` or `test_db`).
- Import the schema and sample data:
  ```bash
  mysql -u root -p onneshon < onneshon.sql
  ```

### 3. Configure Environment
Edit `src/main/resources/application-dev.properties` and `application-prod.properties` for your DB credentials:
```
spring.datasource.url=jdbc:mysql://localhost:3306/onneshon
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 4. Build the Project
```bash
mvn clean install
```

## Environment Configuration
- `application.properties` sets common configs (file upload, security, etc.)
- `application-dev.properties` and `application-prod.properties` set DB and port for dev/prod
- Switch profiles via `spring.profiles.active=dev` or `prod`

## Running the Application
```bash
# For dev profile (default)
mvn spring-boot:run
# Or run the jar
java -jar target/onneshon-blog-api-0.0.1-SNAPSHOT.jar
```
- The API will be available at `http://localhost:1234/` (dev) or `:5000` (prod)

## API Endpoints
### Auth
- `POST /api/v1/auth/register` — Register a new user (multipart/form-data)
- `POST /api/v1/auth/login` — Login and receive JWT

### Users
- `POST /api/user/` — Add user (multipart/form-data)
- `PUT /api/user/{userId}` — Update user
- `DELETE /api/user/{userId}` — Delete user (admin only)
- `GET /api/user/{userId}` — Get user by ID
- `GET /api/users/` — List all users

### Blogs
- `POST /api/blog` — Add blog (multipart/form-data)
- `PUT /api/blog/{blogId}` — Update blog
- `DELETE /api/blog/{blogId}` — Delete blog
- `GET /api/blog/{blogId}` — Get blog by ID
- `GET /api/blogs` — List all blogs (pagination, sorting)
- `GET /api/user/{userId}/blogs` — Blogs by user
- `GET /api/category/{catId}/blogs` — Blogs by category
- `GET /api/blogs/results?search_query=...` — Search blogs

### Categories
- `POST /api/category/` — Add category
- `PUT /api/category/{catId}` — Update category
- `DELETE /api/category/{catId}` — Delete category
- `GET /api/category/{catId}` — Get category by ID
- `GET /api/category/all` — List all categories

### Comments
- `POST /api/blog/{blogId}/comment` — Add comment
- `DELETE /api/comment/{commentId}` — Delete comment
- `GET /api/comment/blog/{blogId}` — Get comments for a blog

### API Documentation
- Swagger UI: `http://localhost:1234/swagger-ui/index.html`

## Connecting the UI
The official React frontend is available at: [https://github.com/ShubratoDn/onneshon-react](https://github.com/ShubratoDn/onneshon-react)
- Clone and run the UI repo for a complete full-stack experience.
- The UI expects the backend to be running at the configured API base URL (see `.env` in the UI repo).

## Contributing
Pull requests are welcome! Please open issues for bugs or feature requests.

## License
This project is licensed under the MIT License. 

## Contact Information
- **Name:** Shubrato Debnath
- **Email:** shubratodn44985@gmail.com 
- **Phone:** +880 1759458961

