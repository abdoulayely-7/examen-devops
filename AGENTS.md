# Development Rules

## General Philosophy

The developer values:
- clean and maintainable code
- SOLID principles
- separation of concerns
- explicit domain modeling
- modular architecture
- testability
- readability over cleverness
- simple solutions over unnecessary abstractions

Do not generate code blindly.

Before implementing a significant feature:
1. Understand the existing architecture.
2. Identify the affected modules.
3. Explain the proposed approach.
4. Identify potential domain and architectural consequences.
5. Then implement.

## Learning Mode

The developer wants to understand the reasoning behind important technical decisions.

For significant architectural or domain changes:
1. Explain the problem.
2. Explain the proposed solution.
3. Explain alternatives.
4. Explain important trade-offs.
5. Then implement.

Do not blindly generate large amounts of code.

When a concept is educationally important, explain it briefly before implementation.

Prefer incremental implementation over generating an entire feature at once.

OpenCode must not automatically code immediately. For significant work, explain first, then implement.

## Architecture

Prefer:
- modular architecture
- Domain-Driven Design when justified
- clear separation between domain, application, infrastructure and presentation
- dependency inversion
- small cohesive classes
- explicit dependencies

Avoid:
- god classes
- god services
- anemic abstractions created without justification
- unnecessary design patterns
- unnecessary interfaces
- premature abstraction
- business logic inside controllers
- business logic inside repositories

## Spring Boot

Use:
- Java 21+
- Spring Boot 3+
- constructor injection
- DTOs at API boundaries
- Bean Validation
- global exception handling
- Spring Data JPA when appropriate
- Flyway for database migrations
- OpenAPI documentation

Avoid:
- field injection
- exposing JPA entities directly through REST APIs
- putting business logic in controllers
- using Optional as method parameters
- returning entities directly from controllers

## Java

Prefer:
- immutable objects when practical
- records for simple DTOs/value-like structures
- enums for constrained domain concepts
- BigDecimal for monetary values
- meaningful names
- small methods
- explicit domain rules

Do not use Lombok unless explicitly requested.

## Testing

Follow TDD when appropriate:

RED
→ write a failing test

GREEN
→ implement the minimum code

REFACTOR
→ improve the design

Tests should focus on behavior rather than implementation details.

Prefer:
- unit tests for domain/application logic
- integration tests where infrastructure behavior matters
- @WebMvcTest for controller/API behavior
- @DataJpaTest for persistence behavior
- @SpringBootTest only when full application integration is actually required

## Database

Use PostgreSQL.

Database schema changes must be performed through Flyway migrations.

Never modify the database schema manually without a corresponding migration.

Consider:
- indexes
- constraints
- foreign keys
- unique constraints
- transaction boundaries
- N+1 queries
- fetch strategies

## API

REST APIs should:
- use appropriate HTTP methods
- use meaningful HTTP status codes
- validate input
- return consistent error responses
- never expose internal implementation details
- use DTOs
- document public endpoints

## Security

Security must be considered for every API feature.

Check:
- authentication
- authorization
- ownership
- role permissions
- input validation
- sensitive data exposure
- IDOR vulnerabilities

Never assume that hiding an endpoint in the frontend is sufficient authorization.

## Code Changes

Before modifying code:
- inspect related classes
- inspect existing tests
- inspect database migrations
- inspect configuration
- follow existing conventions

Do not rewrite unrelated code.

Keep changes focused.

After implementation:
1. Run tests.
2. Run compilation/build.
3. Check for regressions.
4. Review the diff.
5. Explain what changed and why.
