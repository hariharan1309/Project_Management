# Project_Management
 
Task Overview: You are required to design and implement a simple Project Management System 
where users can create, retrieve, update, and delete project information. The application should 
be built using Java 17 and Spring Boot, with an in-memory database like H2 for data persistence.
Detailed Steps:
1. Project Setup: • Initialize a new Spring Boot project using Spring Initializr 
(https://start.spring.io/). • Include dependencies: Spring Web, Spring Data JPA, H2 Database. • Set 
up the project structure with appropriate packages (controllers, services, repositories, models, 
etc.).
2. Database Configuration: • Configure the H2 in-memory database in application.properties. • 
Define the schema for the 'Project' entity (attributes: id, name, description, startDate, endDate, 
etc.).
3. Model Creation: • Create a Project model class in the models package with annotations for JPA 
entity.
4. Repository Layer: • Create a ProjectRepository interface extending JpaRepository to handle 
data operations.
5. Service Layer: • Implement a ProjectService class to handle business logic. • Define methods for 
create, read, update, and delete operations.
6. Controller Layer: • Develop a ProjectController class to handle HTTP requests. • Map CRUD 
operations to RESTful endpoints (e.g., POST /projects, GET /projects/{id}, etc.).
7. CRUD Operations: • Create: Implement an endpoint to add a new project. • Read: Implement 
endpoints to retrieve all projects and a single project by ID. • Update: Implement an endpoint to 
update an existing project. • Delete: Implement an endpoint to delete a project by ID.
8. Exception Handling: • Implement global exception handling using @ControllerAdvice to 
manage exceptions and provide meaningful error messages.
9. Data Validation: • Use Spring Validation to validate input data for create and update 
operations.
10. Testing: • Write unit tests for service layer methods. • Write integration tests for API 
endpoints.
11. Documentation: • Document each API endpoint using Swagger or a similar tool.
12. Submission: • Provide a GitHub repository link containing the complete project. • Include a 
README file with setup instructions and API usage details.

All the steps have been followed and implemented here.
A react-based front end has also been added to visualize the app better

Front-end:
  ReactJS - A JavaScript library for building user interfaces.
  Vite - A fast development build tool for modern web applications.
  TailwindCSS - a utility-first UI library
  
Back-end:
  Spring Boot - An open-source Java-based framework for building microservices and standalone applications.
  
Database:
  H2 - an in-memory database available inbuilt.

For API documentation please visit http://localhost:8090/swagger-ui/index.html#/ after deploying the application

clone the repo and deploy the project local

npm run dev - will run on `http://localhost:5173/`

./mvnw spring-boot:run will run on `http://localhost:8080`.

