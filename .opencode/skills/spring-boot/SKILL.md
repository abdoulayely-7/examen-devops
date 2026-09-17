---
name: spring-boot
description: "Use when writing or modifying Spring Boot code, controllers, services, DTOs, or dependency injection. Enforces project conventions for layering, validation, transactions, and exceptions in Spring Boot applications."
---

# Spring Boot Development

## Controllers

Controllers must remain thin.

A controller is responsible for:
- HTTP request handling
- input validation
- authentication context extraction
- calling the application layer
- mapping the result to an HTTP response

Controllers must not contain business rules.

## Services

Do not automatically create a service for every entity.

Create application services around use cases.

Prefer:

CreateAppointment
CancelAppointment
GetAvailableSlots

over:

AppointmentService with dozens of unrelated methods.

## DTOs

Separate:
- request DTOs
- response DTOs
- domain objects
- persistence entities

Never expose JPA entities directly through public APIs.

## Transactions

Define transaction boundaries at the application/use-case level.

Avoid placing @Transactional everywhere without understanding the transaction boundary.

## Dependency Injection

Use constructor injection.

Do not use field injection.

## Exceptions

Use domain/application exceptions for business failures.

Use a centralized exception handling mechanism.

Do not expose stack traces or internal exception messages to clients.
