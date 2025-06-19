🏋️ Gym Membership Management System

A full-stack Spring Boot application that allows users to register using OTP, log in with session-based authentication, view membership plans, make payments, and receive invoices via email.

---

## 🚀 Live Demo

🔗 [https://gymmembershipmanagement-1.onrender.com](https://gymmembershipmanagement-1.onrender.com)

---

## 🛠️ Tech Stack

- **Backend**: Java, Spring Boot, Spring MVC, Spring Data JPA, Hibernate
- **Frontend**: HTML, CSS, Thymeleaf
- **Database**: MySQL
- **Email Service**: JavaMailSender
- **Build Tool**: Maven
- **Deployment**: Render

---

## ✨ Features

- 🔐 OTP-based user registration via email
- 🔑 Session-based manual login 
- 📋 View and select membership plans (Monthly, Quarterly, Yearly)
- 💳 Make payments through mock payment flow (via REST APIs)
- 📧 Email invoice sent after successful payment
- 🔄 Full RESTful API design (Controller → Service → Repository)

---

## 📸 Screenshots

(Screenshots like registration, membership plan view, email invoice, etc.)
![HomePage](https://github.com/user-attachments/assets/9c0f5160-8b8d-4262-987d-bdc492083471)
![Registration page](https://github.com/user-attachments/assets/3fad926a-4c69-483c-b7aa-b5ed13e13158)
![DashBoard](https://github.com/user-attachments/assets/099c15f8-6a1d-43db-843a-d45c2229d77e)
![PLans](https://github.com/user-attachments/assets/2eea7377-6dc8-44f4-9f51-3dd627b04fdb)
![Payment page](https://github.com/user-attachments/assets/340cb94f-544d-4b55-bda2-a75ed00e3703)
![payment success invoice](https://github.com/user-attachments/assets/82f398b6-0aaf-4826-b7b6-a71aadcd5a8e)

📦Steps on How to Run Locally

### Prerequisites

- Java 17+
- Maven
- MySQL
- IDE (Eclipse / IntelliJ)

### Steps

1.*Clone the Repository**
   ```bash
   git clone https://github.com/Roniit24/GymMembershipManagement.git
   cd GymMembershipManagement

2.**Create the Database**
CREATE DATABASE gym_db;

3.**Configure application.properties**
Edit src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/gym_db
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD

4.**Run the Application**
Use your IDE or command line:
./mvnw spring-boot:run

5.**Access in Browser**
http://localhost:8080/index.html


🧩 Project Structure
src/
└── main/
    ├── java/in/sp/main/
    │   ├── controllers/
    │   ├── service/
    │   ├── repository/
    │   ├── entities/
    │   └── MainClass.java
    └── resources/
        ├── templates/
        ├── static/
        └── application.properties


🙋‍♂️ Author
Rohit Singh
Java Developer | Spring Boot Enthusiast
📧 roniit24@gmail.com
🌐 GitHub Profile

📄 License
This project is for learning and demo purposes. Free to use with credits.
