package com.amit.demo.StudentServer.Controller;

import com.amit.demo.StudentServer.Entity.Student;
import com.amit.demo.StudentServer.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Student> storeStudent(@RequestBody Student student){

       Student result =  studentService.studentValidate(student);

       if(result == null)
           return ResponseEntity.status(400).body(result);

       return ResponseEntity.status(201).body(result);




//        int id = student.getId();
//        int age = student.getAge();
//        String name = student.getName();
//        String department = student.getDepartment();
//
//        return "id: "+ id +
//                ", name: " + name +
//                ", age: " + age +
//                ", department: " + department;
    }
}
