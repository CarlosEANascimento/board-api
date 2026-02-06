# Board Management API

[English](README.en.md) | [Português](README.pt-BR.md)

This project is a RESTful API developed in Java using Spring Boot, designed to manage Boards, Columns, and Cards following a Kanban-style workflow. It was created as a final module project for DIO, focusing on clean architecture, good practices, and real-world backend patterns.

The API allows full management of boards and cards, including card movement between columns, card blocking/unblocking with history, cancellation, and pagination.

## Features

- CRUD operations for Boards
- CRUD operations for Cards
- Card blocking and unblocking
- Card block/unblock history
- Card movement between columns
- Card cancellation
- Paginated listing of Boards
- Cascade validations (DTO → Controller → Service)
- Centralized exception handling
- Logging across all layers

## Technologies

- Java 21
- Spring Boot 4.0.1
- Spring Data JPA
- Spring Web MVC
- Spring Validation
- Hibernate
- MySQL
- Lombok
- SpringDoc OpenAPI (Swagger / OpenAPI 3.0)
- Spring Boot Actuator

## Design Patterns

- MVC (Model-View-Controller)
- DTO Pattern (Data Transfer Objects)
- Service Layer Pattern
- Repository Pattern
- Mapper Pattern
- Global Exception Handler Pattern (@RestControllerAdvice)

## Technical Concepts Applied

- Logging with SLF4J
- Pagination with Pageable and @PageableDefault
- Validation with @NotBlank and @Valid
- Transaction management with @Transactional
- Foreign keys and cascade rules
- One-to-Many and Many-to-One relationships
- Enums for domain state control
- Streams and lambda expressions
- Optional.orElseThrow with custom exceptions
- Dependency Injection

## Domain Entities

- Board
- Columns
- Card
- CardBlocked
- Status (Enum)

## REST Endpoints

### Boards
- POST /boards/new
- GET /boards
- GET /boards/{boardId}
- DELETE /boards/{boardId}

### Cards
- POST /cards/{boardId}/new
- GET /cards/{cardId}/block-history
- PATCH /cards/{cardId}/move
- PATCH /cards/{cardId}/cancel
- PATCH /cards/{cardId}/block
- PATCH /cards/{cardId}/unblock

## Data Transfer Objects (DTOs)

- BoardRequestDTO
- BoardResponseDTO
- CardRequestDTO
- CardResponseDTO
- ColumnsResponseDTO
- CardBlockedResponseDTO
- BlockRequestDTO
- ErrorResponseDTO

## Exception Handling

The API uses a centralized exception handling mechanism with custom exceptions:

- BoardNotFoundException (404)
- CardNotFoundException (404)
- ColumnNotFoundException (404)
- CardBlockNotFoundException (404)
- IllegalArgumentException (400)

## API Documentation

The API is documented using Swagger/OpenAPI and can be accessed via:

- /swagger-ui.html
- /v3/api-docs

## Project Purpose

This project aims to demonstrate the implementation of a well-structured backend application using Spring Boot, applying industry-standard practices such as layered architecture, domain-driven design concepts, and RESTful principles.
