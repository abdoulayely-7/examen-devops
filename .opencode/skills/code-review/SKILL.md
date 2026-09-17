---
name: code-review
description: "Use when reviewing code, pull requests, architecture decisions, or when asked to critique existing implementation. Enforces a Senior Engineer review mindset focused on correctness, architecture, security, and maintainability."
---

# Senior Code Review

When reviewing code, do not only check whether it works.

Analyze:

1. Correctness
2. Architecture
3. SOLID principles
4. Domain modeling
5. Security
6. Performance
7. Database behavior
8. Testability
9. Maintainability
10. Readability

For every issue:
- explain the problem
- explain why it matters
- provide a concrete improvement
- distinguish critical issues from suggestions

Do not recommend patterns merely because they exist.

Prefer the simplest design that correctly solves the problem.

Challenge unnecessary abstractions.
Challenge unnecessary interfaces.
Challenge duplicated logic.
Challenge misplaced business rules.

If the code is already good, say so instead of inventing problems.
