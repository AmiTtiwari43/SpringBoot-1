package com.amit.demo.StudentServer.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateStudentRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private int age;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid Email")
    private String email;
}