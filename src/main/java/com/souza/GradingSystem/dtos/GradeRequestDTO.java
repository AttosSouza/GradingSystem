package com.souza.GradingSystem.dtos;

import java.math.BigDecimal;

public record GradeRequestDTO(BigDecimal value, Long studentId, Long subjectId, Long teacherId) {
}
