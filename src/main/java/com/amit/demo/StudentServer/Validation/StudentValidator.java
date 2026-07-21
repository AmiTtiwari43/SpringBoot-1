package com.amit.demo.StudentServer.Validation;

import com.amit.demo.StudentServer.Exception.DuplicateEmailException;
import com.amit.demo.StudentServer.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentValidator {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void validateDuplicateEmail(String email) {

        if (studentRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already exists.");
        }

    }

}