---
name: testing-tdd
description: "Use when writing tests, implementing business logic with tests, reviewing test coverage, or following Test-Driven Development. Enforces TDD workflow and behavioral test principles."
---

# TDD

When implementing non-trivial business logic, prefer TDD.

Process:

1. Understand the behavior.
2. Write the smallest failing test.
3. Implement the minimum behavior.
4. Run the test.
5. Refactor.
6. Repeat.

Do not write meaningless tests simply to increase coverage.

Tests should describe business behavior.

Prefer:
- Arrange
- Act
- Assert

Tests should have clear names describing the expected behavior.

Avoid excessive mocking.

Mock external dependencies, not domain objects unnecessarily.
