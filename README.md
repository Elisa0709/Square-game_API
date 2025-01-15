# Square Games API

This REST API allows users to manage games, such as creating, updating, deleting, and retrieving game instances and user information.
This API relies on a private dependency, for game management.

### User Management

- **GET /users**: Retrieve all users.
- **POST /users**: Create a new user.
- **GET /users/{id}**: Retrieve a user by their ID.
- **PUT /users/{id}**: Update a user by ID.
- **DELETE /users/{id}**: Delete a user by ID.

### Game Management

- **POST /games**: Create a new game.
- **GET /games**: Retrieve all saved games.
- **GET /games/{gameId}**: Retrieve a specific game by ID.
- **DELETE /games/{gameId}**: Delete a specific game by ID.
- **DELETE /games**: Delete all games.

  ## Technologies Used

- **Spring Boot**: Backend framework for building RESTful APIs.
- **JPA / Hibernate**: Database management.
- **UUID**: Used for unique game identification.
