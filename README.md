# Backend Developer Coding Test

This repository contains solutions for three tasks:

- Task 7: singly linked list lookup.
- Task 8: PostgreSQL schema and query.
- Task 9: Spring Boot quotes API.

## Requirements

- Java 17+
- Maven 3.9+

## Run

```bash
mvn test
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.
Quotes are stored in a local H2 database file under `./data/quotes-db`, so they remain available after server restarts.

## Task 7: Linked List

Solution:

- [`Node.java`](src/main/java/com/example/codingtest/linkedlist/Node.java)
- [`TwoThirdsOfLinkedList.java`](src/main/java/com/example/codingtest/linkedlist/TwoThirdsOfLinkedList.java)

The method returns the node at index `floor(2 * n / 3) - 1` for `n > 1`; otherwise it returns `null`.

Complexity:

- Time: `O(n)`
- Extra memory: `O(1)`
- One pass over the list.

Tests:

- [`TwoThirdsOfLinkedListTest.java`](src/test/java/com/example/codingtest/linkedlist/TwoThirdsOfLinkedListTest.java)

## Task 8: SQL

PostgreSQL solution:

- [`task8_students_disciplines.sql`](src/main/resources/sql/task8_students_disciplines.sql)

The many-to-many relationship is represented by `student_discipline_groups`, which also stores the group number for a student within a discipline.

## Task 9: Quotes API

### Create quote

```bash
curl -X POST http://localhost:8080/quotes \
  -H "Content-Type: application/json" \
  -d '{"author":"Lesya Ukrainka","text":"No, I am alive, I will live forever."}'
```

Response:

```json
{
  "id": 1
}
```

### Get quote by id

```bash
curl http://localhost:8080/quotes/1
```

Response:

```json
{
  "id": 1,
  "author": "Lesya Ukrainka",
  "text": "No, I am alive, I will live forever."
}
```

### Get quotes with pagination

```bash
curl "http://localhost:8080/quotes?page=0&size=10"
```

### Filter quotes by author

```bash
curl "http://localhost:8080/quotes?author=Lesya%20Ukrainka&page=0&size=10"
```

### Update quote

```bash
curl -X PUT http://localhost:8080/quotes/1 \
  -H "Content-Type: application/json" \
  -d '{"author":"Lesya Ukrainka","text":"I have in my heart that which does not die."}'
```

### Delete quote

```bash
curl -X DELETE http://localhost:8080/quotes/1
```

## Validation

Quote fields are validated:

- `author`: required, max 200 characters.
- `text`: required, max 1000 characters.

Invalid requests return HTTP `400` with a JSON error response.

## Project structure

```text
src/
  main/
    java/com/example/codingtest/
      CodingTestApplication.java
      linkedlist/
      quote/
    resources/
      application.properties
      sql/task8_students_disciplines.sql
  test/
    java/com/example/codingtest/
      linkedlist/
      quote/
```
