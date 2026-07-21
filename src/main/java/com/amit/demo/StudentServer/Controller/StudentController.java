package com.amit.demo.StudentServer.Controller;

import com.amit.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.amit.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.amit.demo.StudentServer.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
            @Valid @RequestBody CreateStudentRequestDTO dto) {

        CreateStudentResponseDTO response = studentService.createStudent(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // READ
    @GetMapping("/get/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudentById(
            @PathVariable int id) {

        CreateStudentResponseDTO response = studentService.getStudentById(id);

        return ResponseEntity.ok(response);
    }

    // UPDATE (PUT)
    @PutMapping("/update/{id}")
    public ResponseEntity<CreateStudentResponseDTO> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody CreateStudentRequestDTO dto) {

        CreateStudentResponseDTO response = studentService.putStudent(id, dto);

        return ResponseEntity.ok(response);
    }

    // PARTIAL UPDATE (PATCH)
    @PatchMapping("/patch/{id}")
    public ResponseEntity<CreateStudentResponseDTO> patchStudent(
            @PathVariable int id,
            @RequestBody CreateStudentRequestDTO dto) {

        CreateStudentResponseDTO response = studentService.patchStudent(id, dto);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully.");
    }
}