package com.amit.demo.StudentServer.Controller;

import com.amit.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.amit.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.amit.demo.StudentServer.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> storeStudent(
            @RequestBody CreateStudentRequestDTO requestDTO) {

        CreateStudentResponseDTO responseDTO = studentService.createStudent(requestDTO);

        if (responseDTO == null) {
            return ResponseEntity.badRequest().body("Invalid Input");
        }

        return ResponseEntity.ok(responseDTO);
    }

    // READ
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        CreateStudentResponseDTO responseDTO = studentService.getStudentById(id);

        if (responseDTO == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        return ResponseEntity.ok(responseDTO);
    }

    // PUT
    @PutMapping("/put/{id}")
    public ResponseEntity<?> putStudent(
            @PathVariable int id,
            @RequestBody CreateStudentRequestDTO requestDTO) {

        CreateStudentResponseDTO responseDTO =
                studentService.putStudent(id, requestDTO);

        if (responseDTO == null) {
            return ResponseEntity.badRequest().body("Student not found or Invalid Input");
        }

        return ResponseEntity.ok(responseDTO);
    }

    // PATCH
    @PatchMapping("/patch/{id}")
    public ResponseEntity<?> patchStudent(
            @PathVariable int id,
            @RequestBody CreateStudentRequestDTO requestDTO) {

        CreateStudentResponseDTO responseDTO =
                studentService.patchStudent(id, requestDTO);

        if (responseDTO == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        return ResponseEntity.ok(responseDTO);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {

        boolean deleted = studentService.deleteStudent(id);

        if (!deleted) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        return ResponseEntity.ok("Student deleted successfully");
    }
}