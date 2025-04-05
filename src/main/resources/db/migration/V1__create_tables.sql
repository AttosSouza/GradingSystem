CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('STUDENT', 'TEACHER')),
    professor_id INT NULL,
    CONSTRAINT fk_users_professor FOREIGN KEY (professor_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE subjects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE grades (
    id SERIAL PRIMARY KEY,
    value NUMERIC(4,2) NOT NULL CHECK (value >= 0 AND value <= 10),
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    teacher_id INT NOT NULL,
    CONSTRAINT fk_grades_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_grades_subject FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE,
    CONSTRAINT fk_grades_teacher FOREIGN KEY (teacher_id) REFERENCES users(id) ON DELETE CASCADE
);