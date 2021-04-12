# ToDoEXP
## Game-ify your productivity
ToDoEXP is a Java web application that intends to serve as a simple todo-list that gives its users an additional feel of satisfcation by employing a reward system. Much like in a videogame, ToDoEXP offers its users simple experience points that come along with the tasks they input. As they gather more experience, users will be able to spend them on various in-app purchases. 

### Dependencies
ToDoEXP utilizes Apache Maven to build and manage dependencies. By inspecting the `pom.xml` file, there are several dependencies from the Spring Framework project.

- Spring Data JPA
- Spring Thymeleaf
- Spring Web 
- Spring HATEOAS 
- Postgresql Driver
- Spring Boot Test
- NodeJs 
- Axios

The DBMS utilized is PostgreSQL. If the settings for PostgreSQL is not default, edit `applications.properties` in the project's root folder and edit it accordingly. 

### Developer environment
Because the back-end is written in Java, back-end development utilizes Jetbrains IntelliJ IDEA. For the front-end development, Visual Studio Code is used. However, it is up to the contributor in which tools they are most comfortable with. 

### Progress
#### Front-end
Front-end, and its middleware to the back-end, is developed and maintained by Daine Jadman and Justine Juan

- [ ] Landing page
- [ ] Login page
- [ ] Home page 
- [ ] New task page
- [ ] View task page
- [ ] Delete task page
- [ ] Update task page
- [ ] Shop
- [ ] User profile

#### Back-end
Back-end, and its middleware to the front-end, is developed and maintained by Rafael Gabriel Arceo

##### REST and Database module
- [x] Basic REST implementation
- [x] Proper mapping
- [x] Functional HTTP requests and response
- [x] Mapping of parent-child implementation for tasks and connected through Spring Data JPA
- [ ] HATOEAS refactoring 

##### Task module
- [x] Functional CRUD operations on objects
- [x] OneToMany relationship of ParentTask to ChildTask
- [x] ManyToOne relationship of ChildTask to ParentTask
- [x] No recursion in the relationship testing
- [ ] Experience point assignment to tasks

##### User module
- [x] Storing of user information in the database
- [x] OneToMany relationship implementation with Parent Task
- [x] Allowing access of different users based on REST
- [ ] Login page implementation
- [ ] Security-first approach to user creation

##### Store and Badges module
- [ ] Initial progress
##### 
