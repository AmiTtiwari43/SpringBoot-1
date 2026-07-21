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
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        CreateStudentResponseDTO response = studentService.getStudentById(id);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }

        return ResponseEntity.ok(response);
    }

    // PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<?> putStudent(
            @PathVariable int id,
            @Valid @RequestBody CreateStudentRequestDTO dto) {

        CreateStudentResponseDTO response = studentService.putStudent(id, dto);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }

        return ResponseEntity.ok(response);
    }

    // PATCH
    @PatchMapping("/patch/{id}")
    public ResponseEntity<?> patchStudent(
            @PathVariable int id,
            @RequestBody CreateStudentRequestDTO dto) {

        CreateStudentResponseDTO response = studentService.patchStudent(id, dto);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        boolean deleted = studentService.deleteStudent(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }

        return ResponseEntity.ok("Student deleted successfully");
    }
}