package com.amit.demo.StudentServer.Repository;

import com.amit.demo.StudentServer.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Integer> {








//    public Student save(Student student) {
//        System.out.println("Student information saved");
//        return student;
//    }


}
