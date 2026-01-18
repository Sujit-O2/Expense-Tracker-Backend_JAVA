<h1 align="center">💰 Expense Tracker – Backend</h1>

<p align="center">
  <b>Secure • Scalable • RESTful</b>
</p>

<p align="center">
  <img src="https://readme-typing-svg.demolab.com/?lines=Spring+Boot+Expense+Tracker+API;JWT+Secured+REST+APIs;Auth+%7C+Expenses+%7C+Analytics&font=Fira%20Code&center=true&width=560&height=45&color=6DB33F&vCenter=true&pause=1000&size=22" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen"/>
  <img src="https://img.shields.io/badge/Java-17+-orange"/>
  <img src="https://img.shields.io/badge/JWT-Secure-blue"/>
  <img src="https://img.shields.io/badge/REST-API-success"/>
</p>

---

## 🚀 About

This repository contains the **backend** of the **Expense Tracker application**, built using  
**Spring Boot** and **Spring Security**.

It provides **secure REST APIs** for authentication and expense management,  
and is designed to work seamlessly with the **React frontend** using **JWT authentication**.

---

## ✨ Features

✅ User Signup & Login  
✅ JWT-based Authentication  
✅ Secure password hashing  
✅ Role-based API access  
✅ Add / View / Update / Delete expenses  
✅ Category-based expense management  
✅ User-specific data isolation  
✅ Analytics-ready APIs 📊  
✅ Monthly budget validation 💰  
✅ CORS configured for frontend  
✅ Scalable REST architecture  

---

## 🔐 Authentication Flow

1️⃣ User signs up → password encrypted  
2️⃣ User logs in → JWT generated  
3️⃣ JWT sent via **HTTP-only cookie / Authorization header**  
4️⃣ JWT validated on every request  
5️⃣ User resolved from `SecurityContext`

---

## 📡 API Endpoints

### 🔑 Authentication
POST /auth/users/signup
POST /auth/users/login
POST /users/logout


### 💸 Expenses
GET /expenses
POST /expenses
PUT /expenses/{id}
DELETE /expenses/{id}


### 🧩 Categories
GET /categories


---

## 🧾 Sample Expense JSON

```json
{
  "title": "Groceries",
  "amount": 1500,
  "category": "FOOD",
  "date": "2026-01-15",
  "note": "Weekly shopping"
}
🛠️ Tech Stack
Java 17+

Spring Boot

Spring Security

JWT (JSON Web Token)

Hibernate / JPA

PostgreSQL / MySQL

Maven

⚙️ Configuration
application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expensetracker
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret
jwt.expiration=3600000
▶️ Run the Application
git clone https://github.com/Sujit-O2/Expense_Tracker_Backend
cd Expense_Tracker_Backend
mvn clean install
mvn spring-boot:run
Backend runs at:

http://localhost:8080
📁 Project Structure
src/main/java
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── security
 └── ExpenseTrackerApplication.java
🔗 Frontend Integration
Frontend: React + Tailwind CSS

Authentication: JWT

Requests sent via Axios / Fetch

CORS enabled for http://localhost:5173

👨‍💻 Author
Sujit Swain
Backend Developer – Java & Spring Boot

⭐ Support
If you like this project, don’t forget to star ⭐ the repository
and feel free to contribute 
