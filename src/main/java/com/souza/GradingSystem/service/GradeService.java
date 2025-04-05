package com.souza.GradingSystem.service;

import com.souza.GradingSystem.domain.Grade;
import com.souza.GradingSystem.domain.Subject;
import com.souza.GradingSystem.domain.User;
import com.souza.GradingSystem.dtos.GradeRequestDTO;
import com.souza.GradingSystem.dtos.GradeResponseDTO;
import com.souza.GradingSystem.enums.UserRole;
import com.souza.GradingSystem.exceptions.*;
import com.souza.GradingSystem.repositories.GradeRepository;
import com.souza.GradingSystem.repositories.SubjectRepository;
import com.souza.GradingSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public GradeResponseDTO registerGrade(GradeRequestDTO data) {
        User student = userRepository.findById(data.studentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        User teacher = userRepository.findById(data.teacherId())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
        Subject subject = subjectRepository.findById(data.subjectId())
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found"));

        if (student.getRole() != UserRole.STUDENT) {
            throw new InvalidUserRoleException("Only students can receive grades.");
        }
        if (teacher.getRole() != UserRole.TEACHER) {
            throw new InvalidUserRoleException("Only teachers can assign grades.");
        }
        if (data.value().compareTo(BigDecimal.ZERO) < 0 || data.value().compareTo(new BigDecimal("10.00")) > 0) {
            throw new InvalidGradeValueException("Grade value must be between 0 and 10.");
        }

        Grade grade = new Grade(data.value(), student, subject, teacher);

        Grade savedGrade = gradeRepository.save(grade);

        return new GradeResponseDTO(
                savedGrade.getId(),
                savedGrade.getValue(),
                savedGrade.getStudent().getId(),
                savedGrade.getSubject().getId(),
                savedGrade.getTeacher().getId()
        );
    }

    public BigDecimal calculateAverage(Long studentId, Long subjectId) {
        List<Grade> grades = gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);

        if (grades.isEmpty()) {
            throw new GradeNotFoundException("No grades found for this student and subject.");
        }

        BigDecimal sum = grades.stream()
                .map(Grade::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(new BigDecimal(grades.size()), 2, BigDecimal.ROUND_HALF_UP);
    }

    public boolean isStudentApproved(Long studentId, Long subjectId) {
        BigDecimal average = calculateAverage(studentId, subjectId);
        return average.compareTo(new BigDecimal("7.00")) >= 0;
    }

    public List<GradeResponseDTO> getGradesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Grade> grades;

        if (user.getRole() == UserRole.STUDENT) {
            grades = gradeRepository.findByStudent(user);
        } else if (user.getRole() == UserRole.TEACHER) {
            grades = gradeRepository.findByTeacher(user);
        } else {
            throw new UnauthorizedAccessException("Only students and teachers can view grades.");
        }

        return grades.stream()
                .map(GradeResponseDTO::new)
                .collect(Collectors.toList());
    }

}
