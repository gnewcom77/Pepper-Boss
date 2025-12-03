# 🌶️ Pepper Boss – Hot Sauce Management API  
A backend portfolio project built with **Java 21** and **Spring Boot 3**, designed to manage peppers, ingredients, and sauces. Pepper Boss demonstrates modern REST API design, relational modeling (One-to-Many, Many-to-Many), DTO mapping, error handling, and service-layer testing with JUnit + Mockito.

---

## 🚀 Features

### 🔥 Core Functionality  
- Full CRUD for:
  - **Peppers**
  - **Ingredients**
  - **Sauces**
- Relational modeling:
  - One Pepper → Many Sauces
  - Many Sauces ↔ Many Ingredients  
- DTO endpoints for clean, client-friendly representations  
- Centralized exception handling  
- Service-layer unit tests (Pepper & Ingredient) using Mockito  
- Clean package structure + mapper utilities  
- Sample seed data (via `data.sql`)

---

## 🧱 Tech Stack

| Layer | Technology |
|-------|-------------|
| Language | **Java 21** |
| Framework | **Spring Boot 3.2+** |
| Database | **MySQL** |
| ORM | **Spring Data JPA (Hibernate)** |
| Build Tool | **Maven** |
| Testing | **JUnit 5, Mockito** |
| Utilities | **Lombok**, Java Streams |

---

## 📘 Domain Model Overview

### 🫑 Pepper  
- `pepperId` (Long)  
- `name` (String)  
- `heatLevel` (String)  
- `notes` (String)  
- **Relationships:** One pepper can have many sauces

### 🧄 Ingredient  
- `ingredientId` (Long)  
- `name` (String)  
- `notes` (String)  
- **Relationships:** Many ingredients can be used in many sauces

### 🥫 Sauce  
- `sauceId` (Long)  
- `name` (String)  
- `style` (String)  
- `heatLevel` (String)  
- `notes` (String)  
- `pepper` (Many-to-One Pepper)  
- `ingredients` (Many-to-Many Ingredients)

---

## 📡 REST Endpoints

### 🫑 **Pepper Endpoints**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/peppers` | List all peppers |
| GET | `/peppers/{id}` | Get pepper by ID |
| POST | `/peppers` | Create a pepper |
| PUT | `/peppers/{id}` | Update a pepper |
| DELETE | `/peppers/{id}` | Delete a pepper |
| GET | `/peppers/dto` | Return pepper DTO list |
| GET | `/peppers/{id}/dto` | Return single pepper DTO |

**Sample Pepper POST body:**

```json
{
  "name": "Ghost Pepper",
  "heatLevel": "Very Hot",
  "notes": "A famously intense chili"
}
🧄 Ingredient Endpoints
Method	Endpoint
GET	/ingredients
GET	/ingredients/{id}
POST	/ingredients
PUT	/ingredients/{id}
DELETE	/ingredients/{id}
GET	/ingredients/dto
GET	/ingredients/{id}/dto

Sample Ingredient POST:

json
Copy code
{
  "name": "Garlic",
  "notes": "Fresh minced garlic"
}
🥫 Sauce Endpoints
Method	Endpoint	Notes
GET	/sauces	All sauces
GET	/sauces/{id}	Single sauce
POST	/sauces	Requires pepperId & ingredientIds
PUT	/sauces/{id}	Update sauce
DELETE	/sauces/{id}	Delete sauce
GET	/sauces/dto	Sauce DTO list
GET	/sauces/dto/{id}	DTO by ID
GET	/sauces/by-pepper/{pepperId}	Sauces using a given pepper
GET	/sauces/by-pepper/{pepperId}/dto	DTO versions

Sample Sauce POST:

json
Copy code
{
  "name": "Garlic Lime Blaze",
  "style": "Citrus",
  "heatLevel": "Medium",
  "notes": "Bright and tangy",
  "pepperId": 1,
  "ingredientIds": [1, 2]
}
🧪 Testing
Run tests:

bash
Copy code
mvn test
✔ Coverage Includes
PepperServiceTest

IngredientServiceTest

Tests demonstrate:

Happy-path retrieval

Exception handling (ResourceNotFoundException)

DAO interaction using Mockito

Proper service-layer behavior independent of database

🛠️ How to Run Locally
1. Clone the repository
bash
Copy code
git clone https://github.com/gnewcom77/Pepper-Boss.git
cd Pepper-Boss
2. Create the database
sql
Copy code
CREATE DATABASE pepper_boss;
3. Configure application.yaml (username/password)
4. Start the application
bash
Copy code
mvn spring-boot:run
API will run at:

arduino
Copy code
http://localhost:8080
🔧 Project Structure
bash
Copy code
src/main/java
 └── pepper/boss
      ├── controller
      ├── service
      ├── dao
      ├── entity
      ├── dto
      ├── mapper
      ├── error
      └── PepperBossApplication.java
📈 Future Improvements
Expand unit test coverage

Add Swagger/OpenAPI documentation

Add integration tests using MockMvc

Create a lightweight frontend (React, Vue, or plain JS)

Add authorization layer (API keys/JWT)

🎓 What This Project Demonstrates
Building a clean, layered Spring Boot application

Designing REST endpoints with proper HTTP semantics

Mapping between Entities ↔ DTOs

Managing relational data models

Using JPA repositories effectively

Handling errors with custom exceptions

Writing isolated service-layer tests with Mockito

Clean, readable, production-style code organization

🌶️ Pepper Boss is part of my backend portfolio, created to showcase Spring Boot fundamentals, clean architecture, and relational data modeling.