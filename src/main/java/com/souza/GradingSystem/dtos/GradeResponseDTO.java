package com.souza.GradingSystem.dtos;

import com.souza.GradingSystem.domain.Grade;

import java.math.BigDecimal;

public record GradeResponseDTO(Long id, BigDecimal value, Long studentId, Long subjectId, Long teacherId) {
    public GradeResponseDTO(Grade grade) {
        this(
                grade.getId(),
                grade.getValue(),
                grade.getStudent().getId(),
                grade.getSubject().getId(),
                grade.getTeacher().getId()
        );
    }
}
