---
name: database-migrations
description: "Use when working with PostgreSQL, Flyway migrations, database schema changes, ddl-auto configuration, indexes, or constraints. Enforces versioned, incremental, and safe database schema management."
---

# PostgreSQL and Flyway

PostgreSQL is the default relational database.

All schema changes must be versioned using Flyway.

Never use Hibernate ddl-auto to manage production schema.

Prefer:

spring.jpa.hibernate.ddl-auto=validate

Migrations should:
- be incremental
- be deterministic
- preserve existing data
- include appropriate constraints
- include indexes when justified

Consider query performance and database-level integrity.

Business-critical constraints should be enforced by the database when appropriate.
