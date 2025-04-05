package com.souza.GradingSystem.domain;

import com.souza.GradingSystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "grades")
@Table(name = "grades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chave primária auto-incrementada

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    public Grade(BigDecimal value, User student, Subject subject, User teacher) {
        this.value = value;
        this.student = student;
        this.subject = subject;
        this.teacher = teacher;
    }

    @PrePersist
    @PreUpdate
    private void validateGrade() {
        if (student.getRole() != UserRole.STUDENT) {
            throw new IllegalArgumentException("Only students can receive grades.");
        }
        if (teacher.getRole() != UserRole.TEACHER) {
            throw new IllegalArgumentException("Only teachers can assign grades.");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(new BigDecimal("10.00")) > 0) {
            throw new IllegalArgumentException("Grade value must be between 0 and 10.");
        }
    }
}
