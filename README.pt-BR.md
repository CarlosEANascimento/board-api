# Board Management API

Este projeto é uma API REST desenvolvida em Java com Spring Boot, projetada para gerenciar Boards, Colunas e Cards seguindo um fluxo no estilo Kanban. Ele foi criado como projeto final de módulo da DIO, com foco em arquitetura limpa, boas práticas e padrões usados no mercado.

A API permite o gerenciamento completo de boards e cards, incluindo movimentação de cards entre colunas, bloqueio e desbloqueio com histórico, cancelamento e paginação.

## Funcionalidades

- CRUD de Boards
- CRUD de Cards
- Bloqueio e desbloqueio de Cards
- Histórico de bloqueios e desbloqueios
- Movimentação de Cards entre colunas
- Cancelamento de Cards
- Listagem paginada de Boards
- Validações em cascata (DTO → Controller → Service)
- Tratamento centralizado de exceções
- Logging em todas as camadas

## Tecnologias Utilizadas

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

## Padrões de Design

- MVC (Model-View-Controller)
- DTO Pattern (Data Transfer Objects)
- Service Layer Pattern
- Repository Pattern
- Mapper Pattern
- Global Exception Handler Pattern (@RestControllerAdvice)

## Técnicas e Conceitos Aplicados

- Logging com SLF4J
- Paginação com Pageable e @PageableDefault
- Validações com @NotBlank e @Valid
- Controle transacional com @Transactional
- Chaves estrangeiras e regras de cascade
- Relacionamentos One-to-Many e Many-to-One
- Uso de Enums para controle de estado do domínio
- Streams e expressões lambda
- Uso de Optional.orElseThrow com exceções customizadas
- Injeção de dependência

## Entidades do Domínio

- Board
- Columns
- Card
- CardBlocked
- Status (Enum)

## Endpoints REST

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

## DTOs (Data Transfer Objects)

- BoardRequestDTO
- BoardResponseDTO
- CardRequestDTO
- CardResponseDTO
- ColumnsResponseDTO
- CardBlockedResponseDTO
- BlockRequestDTO
- ErrorResponseDTO

## Tratamento de Exceções

A aplicação utiliza um mecanismo centralizado de tratamento de exceções, com exceções customizadas:

- BoardNotFoundException (404)
- CardNotFoundException (404)
- ColumnNotFoundException (404)
- CardBlockNotFoundException (404)
- IllegalArgumentException (400)

## Documentação da API

A documentação da API é gerada com Swagger/OpenAPI e pode ser acessada em:

- /swagger-ui.html
- /v3/api-docs

## Objetivo do Projeto

Este projeto tem como objetivo demonstrar a implementação de uma aplicação backend bem estruturada utilizando Spring Boot, aplicando práticas de mercado como arquitetura em camadas, princípios REST e conceitos inspirados em Domain-Driven Design.
