package com.souza.GradingSystem.service;

import com.souza.GradingSystem.domain.Grade;
import com.souza.GradingSystem.domain.Subject;
import com.souza.GradingSystem.dtos.SubjectRequestDTO;
import com.souza.GradingSystem.dtos.SubjectResponseDTO;
import com.souza.GradingSystem.exceptions.SubjectNotFoundException;
import com.souza.GradingSystem.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectResponseDTO registerSubject (SubjectRequestDTO data) {
        Subject subject = new Subject(data.name());

        Subject savedSubject = subjectRepository.save(subject);

        return new SubjectResponseDTO(savedSubject.getId(), savedSubject.getName());
    }

    public List<SubjectResponseDTO> getAllSubjects () {
        return subjectRepository.findAll().stream()
                .map(SubjectResponseDTO::new)
                .toList();
    }

    public SubjectResponseDTO updateSubject (Long id, SubjectRequestDTO data) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID: " + id));

        subject.setName(data.name());

        Subject savedSubject = subjectRepository.save(subject);

        return new SubjectResponseDTO(savedSubject.getId(), savedSubject.getName());
    }

    public void deleteSubject (Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID: " + id));

        subjectRepository.delete(subject);
    }

}
