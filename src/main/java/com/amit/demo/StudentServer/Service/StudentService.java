package com.amit.demo.StudentServer.Service;

import com.amit.demo.StudentServer.Entity.Student;
import com.amit.demo.StudentServer.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student studentValidate(Student student){

        int id = student.getId();
        int age = student.getAge();
        String name = student.getName();
        String department = student.getDepartment();

        if(id < 0 || age < 0 || name == null || department == null){
            return null;
        }

        studentRepository.save(student);
        return student;
    }

}
