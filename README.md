# 🚚 Dispatch Management System (Java Client-Server)

This repository contains a university project developed in **Serbian language** for academic purposes as part of the *Software Design* course.

The system represents a **Java client-server desktop application** used for tracking dispatch operations and managing transport orders.

---

## 📌 Project Overview

The application simulates a real-world dispatching system where users (dispatchers) can:

- Log into the system
- Manage business partners
- Create and update transport orders
- Search and filter orders
- Add additional details (e.g. qualifications)
- Communicate with a centralized server

The system follows a **client-server architecture** using sockets and a layered design.

---

## 🏗️ Project Structure

The project consists of three main modules:

### 🔹 Dispecer_Server
- Handles all client requests
- Contains business logic
- Communicates with MySQL database
- Manages system operations

### 🔹 Dispecer_Klijent
- Java Swing desktop application
- GUI for dispatcher interaction
- Sends requests and receives responses

### 🔹 Dispecer_Zajednicki
- Shared module between client and server
- Contains:
  - Domain classes
  - Request/Response communication classes
  - Common logic

---

## ⚙️ Technologies Used

- Java (JDK 24)
- Java Swing
- Socket Programming
- MySQL
- JDBC

---

## 🗄️ Database Setup

1. Open MySQL (Workbench / XAMPP / phpMyAdmin)
2. Import SQL script:
db/seminarskiBaza.sql

3. This will create the database and all required tables.

---

## 🚀 How to Run the Project

⚠️ **IMPORTANT: Follow this order!**

### 1. Configure Database

Open:
Dispecer_Server/config/config.properties

Set your credentials:
url=jdbc:mysql://localhost:3306/seminarski
username=root
password=your_password

---

### 2. Start Server

Run:
Dispecer_Server (main class)

Server will start on port: 9000

---

### 3. Start Client

Run:
Dispecer_Klijent (main class)

- Login screen will appear
- Client connects to server automatically

---

### 4. Use Application

After login you can:

- Manage partners
- Create transport orders
- Search and update orders
- Perform dispatcher operations

---

## 🔌 Communication

- Client sends **Request** object
- Server processes it
- Server returns **Response** object
- Communication is done via Java sockets

---

## ⚠️ Notes

- Project is written in **Serbian language**
- Some paths in code may be **hardcoded (Windows)**
- Default server port: **9000**
- MySQL driver is included in project

---

## 🧪 Run via JAR (optional)

If you use compiled version:
java -jar Dispecer_Server.jar
java -jar Dispecer_Klijent.jar

---

## 🎓 Academic Context

This project was developed for university coursework to demonstrate:

- Client-server architecture
- Layered system design
- Socket communication
- Database integration
- Java Swing GUI development

---

## 👥 Authors

Developed as a student project by Mihajlo Ristanovic.

---

## 📄 License

This project is for educational purposes only.


