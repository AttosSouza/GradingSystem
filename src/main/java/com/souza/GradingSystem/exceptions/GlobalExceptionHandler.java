package com.souza.GradingSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException e){return e.getMessage();}

    @ExceptionHandler(InvalidGradeValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidGradeValueException(InvalidGradeValueException e) {return e.getMessage();}

    @ExceptionHandler(InvalidTeacherAssociationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidTeacherAssociationException(InvalidTeacherAssociationException e) {return e.getMessage();}

    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTeacherNotFoundException(TeacherNotFoundException e){return e.getMessage();}

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleStudentNotFoundException(StudentNotFoundException e){return e.getMessage();}

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException e){return e.getMessage();}

    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleSubjectNotFoundException(SubjectNotFoundException e){return e.getMessage();}

    @ExceptionHandler(GradeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleGradeNotFoundException(GradeNotFoundException e){return e.getMessage();}

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedAccessException(UnauthorizedAccessException e){return e.getMessage();}

    @ExceptionHandler(InvalidUserRoleException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleInvalidUserRoleException(InvalidUserRoleException e) {return e.getMessage();}


}
