package com.amit.demo.StudentServer.Service;

import com.amit.demo.StudentServer.Entity.Student;
import com.amit.demo.StudentServer.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student studentValidate(Student student) {

        if (student == null) {
            return null;
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return null;
        }

        if (student.getDepartment() == null || student.getDepartment().trim().isEmpty()) {
            return null;
        }

        if (student.getAge() <= 0) {
            return null;
        }

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(student);
    }

    // READ
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    // PUT
    public Student putStudent(int id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setDepartment(updatedStudent.getDepartment());

        // Keep old creation time
        existingStudent.setCreatedAt(existingStudent.getCreatedAt());

        // Update time
        existingStudent.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(existingStudent);
    }

    // PATCH
    public Student patchStudent(int id, Student student) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        if (student.getName() != null) {
            existingStudent.setName(student.getName());
        }

        if (student.getDepartment() != null) {
            existingStudent.setDepartment(student.getDepartment());
        }

        if (student.getAge() > 0) {
            existingStudent.setAge(student.getAge());
        }

        existingStudent.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(existingStudent);
    }

    // DELETE
    public boolean deleteStudent(int id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return false;
        }

        studentRepository.delete(student);

        return true;
    }
}