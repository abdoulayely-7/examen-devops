---
name: jpa-hibernate
description: "Use when working with JPA or Hibernate entities, relationships, queries, lazy loading, fetch strategies, cascades, or pagination. Enforces correct JPA/Hibernate usage to avoid performance and data-integrity problems."
---

# JPA / Hibernate

Pay attention to:

- N+1 queries
- lazy loading
- transaction boundaries
- cascade configuration
- orphanRemoval
- entity lifecycle
- fetch joins
- pagination
- indexes

Default to LAZY relationships unless there is a clear reason otherwise.

Do not use EAGER merely to solve LazyInitializationException.

Do not expose entities directly through APIs.

Before adding a relationship, consider:
- ownership
- aggregate boundaries
- cardinality
- database constraints
- query patterns

For collection relationships, consider the performance implications before using them.
