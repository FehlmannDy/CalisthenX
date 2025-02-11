# CalisthenX Full-Stack

**CalisthenX** is a fitness application designed for calisthenics, allowing coaches and athletes to track and optimize their training. The project consists of a **Spring Boot** backend and a **React** frontend.

---

## Technologies Used

### Backend
- **Java 17** and **Spring Boot**  
  - Spring Data JPA (for database access)  
  - Spring Security (for authentication and role management)  
- **PostgreSQL** for data persistence  
- **Docker & Docker Compose** for containerization  
- **Maven** for dependency management and project build  

### Frontend
- **React** (created with Next.js)  
- **React Router** for navigation  
- **Axios** for API communication  
- **Chart.js / Recharts** for data visualization  
- (UI libraries like Material-UI can be added if needed)

---

## Key Features

- **User Management**  
  - Authentication  
  - Role-based access control (Coach and Athlete)  
  - Profile management  

- **Workout Tracking**  
  - Recording workout sessions, including:  
    - List of exercises performed  
    - Sets with repetitions or duration (and method used: RIR, ENOM, AMRAP, etc.)  
    - Athletes can mark a session as completed and specify the finishing time  

- **Coach Feedback**  
  - Coaches can provide overall session comments and attach a video URL for feedback  
  - Individual exercise comments can also be added if necessary  

- **Data Visualization**  
  - Graphs and dashboards to analyze progress and performance  

---

## Installation & Running the Project

### Running the Backend with Docker Compose

1. **Clone the Repository**  
   ```bash
   git clone <REPO_URL>
   cd caliscoach-backend
   ```
   
2. **Check and Modify** docker-compose.yml if needed
This file configures PostgreSQL and the backend.

3. **Start the Services**
   ```bash
   docker-compose up --build
   ```
   Only PostgreSQL will be running on port 5432 yet !

### Running the Backend Locally (without Docker)
Coming soon

### Running the Frontend
Coming soon

---

## Project Structure
Coming soon

---

CalisCoach aims to provide a modern solution for optimizing calisthenics training, combining a robust backend architecture with an intuitive user interface.
