package com.amit.demo.StudentServer.Service;

import com.amit.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.amit.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.amit.demo.StudentServer.Entity.Student;
import com.amit.demo.StudentServer.Exception.DuplicateEmailException;
import com.amit.demo.StudentServer.Repository.StudentRepository;
import com.amit.demo.StudentServer.Validation.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          StudentValidator studentValidator) {
        this.studentRepository = studentRepository;
        this.studentValidator = studentValidator;
    }

    // CREATE
    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new RuntimeException("Request body cannot be null");
        }

        studentValidator.validateDuplicateEmail(requestDTO.getEmail());

        Student student = new Student();

        student.setName(requestDTO.getName().trim());
        student.setAge(requestDTO.getAge());
        student.setDepartment(requestDTO.getDepartment().trim());
        student.setEmail(requestDTO.getEmail().trim());

        return mapToResponseDTO(studentRepository.save(student));
    }

    // READ
    public CreateStudentResponseDTO getStudentById(int id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return mapToResponseDTO(student);
    }

    // PUT
    public CreateStudentResponseDTO putStudent(int id, CreateStudentRequestDTO requestDTO) {

        if (requestDTO == null) {
            throw new RuntimeException("Request body cannot be null");
        }

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!existingStudent.getEmail().equalsIgnoreCase(requestDTO.getEmail())
                && studentRepository.existsByEmail(requestDTO.getEmail())) {

            throw new DuplicateEmailException("Email already exists.");
        }

        existingStudent.setName(requestDTO.getName().trim());
        existingStudent.setAge(requestDTO.getAge());
        existingStudent.setDepartment(requestDTO.getDepartment().trim());
        existingStudent.setEmail(requestDTO.getEmail().trim());

        return mapToResponseDTO(studentRepository.save(existingStudent));
    }

    // PATCH
    public CreateStudentResponseDTO patchStudent(int id, CreateStudentRequestDTO requestDTO) {

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

        if (requestDTO.getEmail() != null && !requestDTO.getEmail().trim().isEmpty()) {

            if (!existingStudent.getEmail().equalsIgnoreCase(requestDTO.getEmail())
                    && studentRepository.existsByEmail(requestDTO.getEmail())) {

                throw new DuplicateEmailException("Email already exists.");
            }

            existingStudent.setEmail(requestDTO.getEmail().trim());
        }

        return mapToResponseDTO(studentRepository.save(existingStudent));
    }

    // DELETE
    public boolean deleteStudent(int id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);

        return true;
    }

    // DTO Mapping
    private CreateStudentResponseDTO mapToResponseDTO(Student student) {

        CreateStudentResponseDTO responseDTO = new CreateStudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setDepartment(student.getDepartment());
        responseDTO.setEmail(student.getEmail());

        return responseDTO;
    }
}