package com.souza.GradingSystem.controller;

import com.souza.GradingSystem.dtos.GradeResponseDTO;
import com.souza.GradingSystem.dtos.SubjectRequestDTO;
import com.souza.GradingSystem.dtos.SubjectResponseDTO;
import com.souza.GradingSystem.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponseDTO> postSubject(@RequestBody @Valid SubjectRequestDTO body) {
        SubjectResponseDTO savedSubject = subjectService.registerSubject(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>>  getSubjects() {
        List<SubjectResponseDTO> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> updateSubject(@PathVariable Long id,
                                              @RequestBody @Valid SubjectRequestDTO body) {
        SubjectResponseDTO updatedSubject = subjectService.updateSubject(id, body);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }




}
