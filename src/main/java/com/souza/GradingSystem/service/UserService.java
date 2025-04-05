package com.souza.GradingSystem.service;

import com.souza.GradingSystem.domain.User;
import com.souza.GradingSystem.dtos.RegisterDTO;
import com.souza.GradingSystem.enums.UserRole;
import com.souza.GradingSystem.exceptions.EmailAlreadyRegisteredException;
import com.souza.GradingSystem.exceptions.InvalidTeacherAssociationException;
import com.souza.GradingSystem.exceptions.TeacherNotFoundException;
import com.souza.GradingSystem.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterDTO data) {
        Optional<UserDetails> existingUser = repository.findByEmail(data.email());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyRegisteredException("Email already registered!");
        }

        if (data.role() == UserRole.STUDENT && data.teacherId() == null) {
            throw new InvalidTeacherAssociationException("Students must have a teacher associated with them.");
        }

        User teacher = null;
        if (data.teacherId() != null) {
            teacher = repository.findById(data.teacherId()).orElseThrow(
                    () -> new TeacherNotFoundException("Teacher not found!")
            );
        }

        if (data.role() == UserRole.TEACHER && teacher != null) {
            throw new InvalidTeacherAssociationException("A teacher cannot have another teacher associated with them.");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role(), teacher);

        return repository.save(newUser);
    }
}
