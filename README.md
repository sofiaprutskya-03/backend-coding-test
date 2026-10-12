# Backend Coding Test

This repository contains solutions for three tasks:

1. Finding the node at index `floor(2 * n / 3) - 1` in a singly linked list.
2. PostgreSQL schema and query for students, disciplines, and discipline groups.
3. Spring Boot REST API for sharing quotes.

## Requirements

- Java 21
- Maven 3.9+

## Run tests

```bash
mvn test
```

## Run the quotes API

```bash
mvn spring-boot:run
```

The API starts on:

```text
http://localhost:8080
```

Quotes are stored in a local H2 database file under `./data/quotes-db`, so data stays available after server restarts.

## Publish to GitHub

Create a public repository on GitHub, for example:

```text
backend-coding-test
```

Then connect this local repository to GitHub:

```bash
git remote add origin https://github.com/YOUR_USERNAME/backend-coding-test.git
git push -u origin main
```

If the remote already exists, use:

```bash
git remote set-url origin https://github.com/YOUR_USERNAME/backend-coding-test.git
git push -u origin main
```

## Quotes API

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

## Task 1: linked list

Solution:

- [`src/main/java/com/example/codingtest/linkedlist/Node.java`](src/main/java/com/example/codingtest/linkedlist/Node.java)
- [`src/main/java/com/example/codingtest/linkedlist/TwoThirdsOfLinkedList.java`](src/main/java/com/example/codingtest/linkedlist/TwoThirdsOfLinkedList.java)

Algorithm:

- If the list has `0` or `1` element, return `null`.
- During one traversal, keep the current length and the current answer node.
- When `floor(2 * length / 3) - 1` increases, move the answer node one step forward.

Complexity:

- Time: `O(n)`
- Extra memory: `O(1)`
- One pass over the list.

## Task 8: SQL

PostgreSQL solution:

- [`src/main/resources/sql/task8_students_disciplines.sql`](src/main/resources/sql/task8_students_disciplines.sql)

The many-to-many relationship is represented by `student_discipline_groups`, which also stores the group number for a student within a discipline.

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
