package com.souza.GradingSystem.dtos;

import com.souza.GradingSystem.domain.User;
import com.souza.GradingSystem.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role, Long teacherId) {
}
