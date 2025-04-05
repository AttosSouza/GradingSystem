package com.souza.GradingSystem.repositories;

import com.souza.GradingSystem.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
