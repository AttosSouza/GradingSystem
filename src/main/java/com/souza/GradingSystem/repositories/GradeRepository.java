package com.souza.GradingSystem.repositories;

import com.souza.GradingSystem.domain.Grade;
import com.souza.GradingSystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(User student);
    List<Grade> findByTeacher(User teacher);
    List<Grade> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
}
