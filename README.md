# 🌶️ Pepper Boss – Hot Sauce Management API

A backend portfolio project built with **Java 21** and **Spring Boot 3**, designed to manage peppers, ingredients, and hot sauces. Pepper Boss demonstrates REST API design, relational database modeling, DTO mapping, centralized error handling, and service-layer unit testing.

---

## 🚀 Features

- Full CRUD for **Peppers**, **Ingredients**, and **Sauces**
- Relational modeling: One Pepper → Many Sauces, Many Sauces ↔ Many Ingredients
- DTO endpoints for clean, client-friendly data representations
- Centralized exception handling with custom error responses
- Service-layer unit tests using **JUnit 5** and **Mockito**
- Sample seed data via `data.sql`

---

## 🧱 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.2+ |
| Database | MySQL |
| ORM | Spring Data JPA (Hibernate) |
| Build Tool | Maven |
| Testing | JUnit 5, Mockito |
| Utilities | Lombok |

---

## 📘 Domain Model

**Pepper** – name, heat level (with SHU range), flavor notes  
**Ingredient** – name, preparation notes  
**Sauce** – name, style, heat level, notes; linked to one Pepper and many Ingredients

---

## 📡 REST Endpoints

### Peppers
| Method | Endpoint | Description |
|---|---|---|
| GET | `/peppers` | List all peppers |
| GET | `/peppers/{id}` | Get pepper by ID |
| POST | `/peppers` | Create a pepper |
| PUT | `/peppers/{id}` | Update a pepper |
| DELETE | `/peppers/{id}` | Delete a pepper |
| GET | `/peppers/dto` | DTO list |
| GET | `/peppers/{id}/dto` | Single pepper DTO |

### Ingredients
| Method | Endpoint |
|---|---|
| GET | `/ingredients` |
| GET | `/ingredients/{id}` |
| POST | `/ingredients` |
| PUT | `/ingredients/{id}` |
| DELETE | `/ingredients/{id}` |
| GET | `/ingredients/dto` |
| GET | `/ingredients/{id}/dto` |

### Sauces
| Method | Endpoint | Notes |
|---|---|---|
| GET | `/sauces` | All sauces |
| GET | `/sauces/{id}` | Single sauce |
| POST | `/sauces` | Requires pepperId & ingredientIds |
| PUT | `/sauces/{id}` | Update sauce |
| DELETE | `/sauces/{id}` | Delete sauce |
| GET | `/sauces/dto` | DTO list |
| GET | `/sauces/dto/{id}` | DTO by ID |
| GET | `/sauces/by-pepper/{pepperId}` | Sauces using a given pepper |

---

## 📦 Sample Request Bodies

**Create a Pepper:**
```json
{
  "name": "Ghost Pepper",
  "heatLevel": "Very Hot: 855,000 - 1,041,427 SHU",
  "notes": "A famously intense chili — handle with care"
}
```

**Create a Sauce:**
```json
{
  "name": "Garlic Lime Blaze",
  "style": "Citrus",
  "heatLevel": "Medium",
  "notes": "Bright and tangy",
  "pepperId": 1,
  "ingredientIds": [1, 2]
}
```

---

## 🧪 Testing

```bash
mvn test
```

Unit tests cover the **Pepper** and **Ingredient** service layers, including:
- Happy-path retrieval
- Exception handling (`ResourceNotFoundException`)
- Mocked DAO interactions using Mockito
- Service behavior verified independently of the database

---

## 🛠️ How to Run Locally

**Prerequisites:** Java 21+, Maven 3.9+, MySQL 8.x

```bash
# 1. Clone the repo
git clone https://github.com/gnewcom77/Pepper-Boss.git
cd Pepper-Boss

# 2. Create the database
CREATE DATABASE pepper_boss;

# 3. Update src/main/resources/application.yaml with your MySQL credentials

# 4. Run the app
mvn spring-boot:run
```

API runs at `http://localhost:8080`

---

## 🔧 Project Structure

```
src/main/java/pepper/boss
├── controller
├── service
├── dao
├── entity
├── dto
├── mapper
├── error
└── PepperBossApplication.java
```

---

## 💡 What I Learned

- How to design and implement a layered Spring Boot application from scratch
- Managing One-to-Many and Many-to-Many relationships with JPA
- Mapping between Entities and DTOs to keep API responses clean
- Writing isolated service-layer tests with Mockito
- Structuring a backend project the way a real engineering team would

---

## 📈 Future Improvements

- Expand unit test coverage to controllers
- Add Swagger/OpenAPI documentation
- Add integration tests using MockMvc
- Add authentication layer (JWT)
- Build a lightweight frontend

---

## 📄 License

MIT © Garrett Newcomer
