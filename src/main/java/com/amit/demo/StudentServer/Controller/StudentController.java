package com.amit.demo.StudentServer.Controller;

import com.amit.demo.StudentServer.Entity.Student;
import com.amit.demo.StudentServer.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    //field injection
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //1. Store the student -- POST
    @PostMapping ("/create")
    public ResponseEntity<?> storeStudent(@RequestBody Student student) {

        Student result = studentService.studentValidate(student);

        if (result == null)
            return ResponseEntity.status(400).body("Invalid Input");

        return ResponseEntity.status(201).body(result);

    }

    @GetMapping ("/get/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity.status(200).body(student);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> putStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        Student updatedStudent = studentService.putStudent(id, student);

        if (updatedStudent == null) {
            return ResponseEntity.status(404).body("Student not found");
        }

        return ResponseEntity.ok(updatedStudent);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<?> patchStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        Student updatedStudent = studentService.patchStudent(id, student);

        if (updatedStudent == null) {
            return ResponseEntity.status(404).body("Student not found");
        }

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {

        boolean deleted = studentService.deleteStudent(id);

        if (!deleted) {
            return ResponseEntity.status(404).body("Student not found");
        }

        return ResponseEntity.ok("Student deleted successfully");
    }

}
