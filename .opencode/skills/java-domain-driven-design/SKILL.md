---
name: java-domain-driven-design
description: "Use when designing or analyzing domain models, aggregates, entities, value objects, bounded contexts, or repository interfaces in Java. Enforces DDD principles when domain complexity justifies them."
---

# Domain-Driven Design

Use DDD when the domain complexity justifies it.

Do not introduce DDD ceremony merely for the sake of architecture.

Identify:
- entities
- value objects
- aggregates
- aggregate roots
- domain services
- domain events
- repositories
- bounded contexts

Prefer rich domain models when business rules belong to the domain.

Avoid anemic domain models when significant business invariants exist.

An aggregate should protect its invariants.

Do not allow arbitrary modification of aggregate internals.

Value Objects should:
- be immutable
- represent a domain concept
- validate their invariants
- be compared by value

Recognize that not every application needs DDD. For simple CRUD operations, a simpler layered design is often more appropriate.
