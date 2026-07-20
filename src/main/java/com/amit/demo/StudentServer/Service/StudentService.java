package com.amit.demo.StudentServer.Service;

import com.amit.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.amit.demo.StudentServer.DTO.CreateStudentResponseDTO;
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
    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO requestDTO) {

        if (requestDTO == null) {
            return null;
        }

        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            return null;
        }

        if (requestDTO.getDepartment() == null || requestDTO.getDepartment().trim().isEmpty()) {
            return null;
        }

        if (requestDTO.getAge() <= 0) {
            return null;
        }

        Student student = new Student();
        student.setName(requestDTO.getName().trim());
        student.setAge(requestDTO.getAge());
        student.setDepartment(requestDTO.getDepartment().trim());

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(student);

        return mapToResponseDTO(savedStudent);
    }

    // READ
    public CreateStudentResponseDTO getStudentById(int id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return null;
        }

        return mapToResponseDTO(student);
    }

    // PUT
    public CreateStudentResponseDTO putStudent(
            int id,
            CreateStudentRequestDTO requestDTO) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null || requestDTO == null) {
            return null;
        }

        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            return null;
        }

        if (requestDTO.getDepartment() == null || requestDTO.getDepartment().trim().isEmpty()) {
            return null;
        }

        if (requestDTO.getAge() <= 0) {
            return null;
        }

        existingStudent.setName(requestDTO.getName().trim());
        existingStudent.setAge(requestDTO.getAge());
        existingStudent.setDepartment(requestDTO.getDepartment().trim());

        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = studentRepository.save(existingStudent);

        return mapToResponseDTO(updatedStudent);
    }

    // PATCH
    public CreateStudentResponseDTO patchStudent(
            int id,
            CreateStudentRequestDTO requestDTO) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null || requestDTO == null) {
            return null;
        }

        if (requestDTO.getName() != null &&
                !requestDTO.getName().trim().isEmpty()) {

            existingStudent.setName(requestDTO.getName().trim());
        }

        if (requestDTO.getDepartment() != null &&
                !requestDTO.getDepartment().trim().isEmpty()) {

            existingStudent.setDepartment(requestDTO.getDepartment().trim());
        }

        if (requestDTO.getAge() > 0) {
            existingStudent.setAge(requestDTO.getAge());
        }

        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = studentRepository.save(existingStudent);

        return mapToResponseDTO(updatedStudent);
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

    // Entity -> Response DTO
    private CreateStudentResponseDTO mapToResponseDTO(Student student) {

        CreateStudentResponseDTO responseDTO =
                new CreateStudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setDepartment(student.getDepartment());

        return responseDTO;
    }
}