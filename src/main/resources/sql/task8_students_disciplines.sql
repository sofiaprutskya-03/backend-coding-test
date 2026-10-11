CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(30),
    admission_year INTEGER NOT NULL CHECK (admission_year >= 1900)
);

CREATE TABLE disciplines (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    credits NUMERIC(4, 1) NOT NULL CHECK (credits > 0),
    hours INTEGER NOT NULL CHECK (hours > 0)
);

CREATE TABLE student_discipline_groups (
    student_id BIGINT NOT NULL REFERENCES students(id) ON DELETE CASCADE,
    discipline_id BIGINT NOT NULL REFERENCES disciplines(id) ON DELETE CASCADE,
    group_number INTEGER NOT NULL CHECK (group_number > 0),
    PRIMARY KEY (student_id, discipline_id)
);

CREATE INDEX idx_disciplines_name ON disciplines(name);
CREATE INDEX idx_student_discipline_groups_group_number
    ON student_discipline_groups(group_number);

SELECT s.full_name, s.email
FROM students s
JOIN student_discipline_groups sdg ON sdg.student_id = s.id
JOIN disciplines d ON d.id = sdg.discipline_id
WHERE d.name = 'Програмування'
  AND sdg.group_number = 5
ORDER BY s.full_name;
