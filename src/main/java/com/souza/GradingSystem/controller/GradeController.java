package com.souza.GradingSystem.controller;

import com.souza.GradingSystem.dtos.GradeRequestDTO;
import com.souza.GradingSystem.dtos.GradeResponseDTO;
import com.souza.GradingSystem.dtos.SubjectResponseDTO;
import com.souza.GradingSystem.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;


    @PostMapping
    public ResponseEntity<GradeResponseDTO> postGrade(@RequestBody @Valid GradeRequestDTO body) {
        GradeResponseDTO gradeResponse = gradeService.registerGrade(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeResponse);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<GradeResponseDTO>> getGradeByUserId(@PathVariable @Valid Long id) {
        List<GradeResponseDTO> grades = gradeService.getGradesByUser(id);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/average/{studentId}/{subjectId}")
    public Map<String, BigDecimal> calculateAverage(@PathVariable Long studentId, @PathVariable Long subjectId) {
        BigDecimal average = gradeService.calculateAverage(studentId, subjectId);
        return Map.of("average", average);
    }

    @GetMapping("/approved/{studentId}/{subjectId}")
    public Map<String, Boolean> isStudentApproved(@PathVariable Long studentId, @PathVariable Long subjectId) {
        boolean approved = gradeService.isStudentApproved(studentId, subjectId);
        return Map.of("approved", approved);
    }

}
