# 🏢 User Management Application  

Efficiently manage users within an organization with this full-stack **User Management System**. The application allows administrators to **create, read, update, and delete (CRUD)** user records, assign roles, and track user details.  

Built using **Spring Boot** for the backend and **Thymeleaf** with **Bootstrap** for a responsive UI, this system ensures smooth user management operations.

---

## ✨ Features  

✅ **CRUD Operations** – Add, edit, view, and delete user records  
✅ **Role Management** – Assign roles to control user access levels  
✅ **User List View** – Display users in a tabular format with ID, name, email, and role  
✅ **Search & Filter** – Find users easily with a search function  
✅ **Responsive UI** – Clean and mobile-friendly interface using Bootstrap  
✅ **Database Integration** – Uses MySQL for storing user information  

---

## 🚀 Tech Stack  

| Layer       | Technology Used |
|------------|----------------|
| **Backend** | Java, Spring Boot, Spring Data JPA |
| **Frontend** | Thymeleaf, HTML, CSS, Bootstrap |
| **Database** | MySQL (can be configured to PostgreSQL) |
| **Tools** | Spring Boot DevTools, Maven |

---

## 🔧 Installation & Setup  

### 📌 Prerequisites  
Ensure you have the following installed:  
- [Java JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)  
- [Maven](https://maven.apache.org/download.cgi)  
- [MySQL Server](https://dev.mysql.com/downloads/mysql/)  

### 📥 Clone the Repository  
```bash
git clone https://github.com/KPISTOLAS/UserManaagment.git
cd UserManaagment

---
## 📝 MIT License

```bash
MIT License

Copyright (c) 2025 Pistolas Konstantinos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
### 🗂️ Project Structure
```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.management
│   │   │       ├── controllers  # Controllers handle HTTP requests and responses
│   │   │       ├── models       # Domain models representing the database entities
│   │   │       ├── request      # Classes and objects used to handle and validate user input in HTTP requests
│   │   │       ├── security     # Security-related configurations like authentication, authorization, and user roles
│   │   │       ├── repositories # Repository interfaces for data access
│   │   │       └── services     # Business logic
│   │   ├── resources
│   │   │   ├── static           # Static assets like CSS, images, and JS
│   │   │   └── templates        # Thymeleaf templates for the views
│   └── test                     # Unit and integration tests
├── pom.xml                      # Project dependencies and build configuration
└── README.md
```
## 🌐 More
- **Access the app at**:
http://localhost:8080
- **application.properties**:
```bash
#Name of aplication
spring.application.name=Company

# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/company_db
spring.datasource.username=root
spring.datasource.password=------
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate (JPA) Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Secret Key (Change this in production)
app.jwt.secret=MySuperSecretKeyForJWT
app.jwt.expiration=3600000
```
