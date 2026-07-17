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

}
