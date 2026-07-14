package com.amit.demo.StudentServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentServer {
    //1. Store the student -- POST
    @PostMapping ("/create")
    public String storeStudent(){
        return """
            id : 1
            name : Amit
            Department : CSE
            age : 22
        """;
    }

    //2. Read the student -- GET
    //3. Update the student -- PUT/PATCH
    //4. Delete the student -- DELETE
}
