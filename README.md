# Pepper Boss API 🌶️  
**RESTful Web API for managing peppers, sauces and their ingredients**  
Tech: `Java 17` • `Spring Boot 3` • `MySQL` • `Maven` • `Lombok`

---

## Overview  
Pepper Boss is a backend application I built to demonstrate how to design and implement a complete RESTful service with a realistic domain. The API supports:

- Modeling spicy-food entities (Peppers → Sauces → Ingredients)  
- A many-to-many relationship (Sauces ⇄ Ingredients)  
- Full CRUD operations, layered architecture, and relational database design

I built this project as part of my transition into a backend development role: I wanted to show that I can design data models, build APIs, and produce code someone else can run.

---

## Data Model  
**Key Entities:**

- **Pepper** – name, heat (Scoville units), flavorNotes  
- **Sauce** – name, brand, heatRating, notes  
- **Ingredient** – name, category, notes  
- **SauceIngredient** – join table linking Sauces and Ingredients, with fields: quantity, unit  

*(See the ERD image for reference.)*

---

## Features  
- CRUD endpoints for Peppers, Sauces and Ingredients  
- Many-to-many join relationship between Sauces and Ingredients  
- JSON REST endpoints using conventional plural paths  
- Clean architecture: Entities → Repositories → Controllers  
- Seed data optionally available via `data.sql`  

---

## Tech Stack  
- **Language:** Java 17+  
- **Framework:** Spring Boot 3  
- **Database:** MySQL 8.x  
- **ORM / JPA:** Spring Data JPA / Hibernate  
- **Build Tool:** Maven  
- **Other Tools:** Lombok, ARC/Postman (for testing), Git/GitHub  

---

## Getting Started  

### Prerequisites  
- Java 17+ installed  
- Maven 3.9+ installed  
- MySQL 8.x (or compatible) running locally  

### Setup  
1. Clone the repo:  
   ```bash
   git clone https://github.com/gnewcom77/Promineo-Tech-Final-Project-
   cd Promineo-Tech-Final-Project-
   ```
2. Create the database (example):  
   ```sql
   CREATE DATABASE pepper_boss CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
3. Update `src/main/resources/application.yaml` (or `application.properties`) with your MySQL username/password.  
4. (Optional) Load seed data using `data.sql`.  
5. Run the application:  
   ```bash
   mvn spring-boot:run
   ```

---

## Example Endpoints  
### Peppers  
- `GET /peppers`  
- `GET /peppers/{id}`  
- `POST /peppers`  
- `PUT /peppers/{id}`  
- `DELETE /peppers/{id}`  

### Sauces  
- `GET /sauces`  
- `GET /sauces/{id}`  
- `POST /sauces`  
- `PUT /sauces/{id}`  
- `DELETE /sauces/{id}`  

### Ingredients  
- `GET /ingredients`  
- `GET /ingredients/{id}`  
- `POST /ingredients`  
- `PUT /ingredients/{id}`  
- `DELETE /ingredients/{id}`  

---

## What I Learned  
- How to map many-to-many relationships in JPA using a join entity (SauceIngredient)  
- How to structure a backend project with clear separation of concerns  
- How to design RESTful CRUD endpoints  
- How to set up a MySQL database and configure Spring Boot  
- How to write clean documentation others can follow  

---

## Future Improvements  
- Pagination and filtering  
- Add authentication (JWT)  
- Dockerize the app + MySQL  
- Add unit/integration tests  
- Deploy a live demo  

---

## License  
MIT © Garrett Newcomer
