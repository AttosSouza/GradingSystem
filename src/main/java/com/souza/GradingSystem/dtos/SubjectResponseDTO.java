package com.souza.GradingSystem.dtos;

import com.souza.GradingSystem.domain.Subject;

public record SubjectResponseDTO(Long id, String name) {
    public SubjectResponseDTO(Subject subject) {
        this(subject.getId(), subject.getName());
    }
}
