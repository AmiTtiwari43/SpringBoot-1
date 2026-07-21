package com.amit.demo.StudentServer.Service;

import com.amit.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.amit.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.amit.demo.StudentServer.Entity.Student;
import com.amit.demo.StudentServer.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new RuntimeException("Request body cannot be null");
        }

        Student student = new Student();
        student.setName(requestDTO.getName() != null ? requestDTO.getName().trim() : null);
        student.setAge(requestDTO.getAge());
        student.setDepartment(requestDTO.getDepartment() != null ? requestDTO.getDepartment().trim() : null);

        return mapToResponseDTO(studentRepository.save(student));
    }

    public CreateStudentResponseDTO getStudentById(int id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return mapToResponseDTO(student);
    }

    public CreateStudentResponseDTO putStudent(int id, CreateStudentRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new RuntimeException("Request body cannot be null");
        }

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setName(requestDTO.getName() != null ? requestDTO.getName().trim() : null);
        existingStudent.setAge(requestDTO.getAge());
        existingStudent.setDepartment(requestDTO.getDepartment() != null ? requestDTO.getDepartment().trim() : null);

        return mapToResponseDTO(studentRepository.save(existingStudent));
    }

    public CreateStudentResponseDTO patchStudent(int id, CreateStudentRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new RuntimeException("Request body cannot be null");
        }

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (requestDTO.getName() != null && !requestDTO.getName().trim().isEmpty()) {
            existingStudent.setName(requestDTO.getName().trim());
        }

        if (requestDTO.getDepartment() != null && !requestDTO.getDepartment().trim().isEmpty()) {
            existingStudent.setDepartment(requestDTO.getDepartment().trim());
        }

        if (requestDTO.getAge() > 0) {
            existingStudent.setAge(requestDTO.getAge());
        }

        return mapToResponseDTO(studentRepository.save(existingStudent));
    }

    public boolean deleteStudent(int id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);

        return true;
    }

    private CreateStudentResponseDTO mapToResponseDTO(Student student) {

        CreateStudentResponseDTO responseDTO = new CreateStudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setDepartment(student.getDepartment());

        return responseDTO;
    }
}