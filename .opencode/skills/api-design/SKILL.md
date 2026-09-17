---
name: api-design
description: "Use when designing or reviewing REST APIs, endpoint contracts, HTTP methods, status codes, error responses, pagination, validation, or OpenAPI documentation. Enforces consistent and clean REST API design."
---

# REST API Design

Use resource-oriented REST APIs.

Use appropriate:
- HTTP methods
- status codes
- pagination
- filtering
- sorting
- validation
- error responses

Use DTOs.

Use a consistent error structure.

Example:

```json
{
  "status": 400,
  "code": "VALIDATION_ERROR",
  "message": "...",
  "errors": [...]
}
```

Do not return stack traces.

Do not leak database or internal implementation details.

API contracts should be explicit and documented with OpenAPI.
