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

    public Student getStudentById(int id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student putStudent(int id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setDepartment(updatedStudent.getDepartment());

        return studentRepository.save(existingStudent);
    }


    public Student patchStudent(int id, Student student) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        if (student.getName() != null) {
            existingStudent.setName(student.getName());
        }

        if (student.getDepartment() != null) {
            existingStudent.setDepartment(student.getDepartment());
        }

        if (student.getAge() != 0) {
            existingStudent.setAge(student.getAge());
        }

        return studentRepository.save(existingStudent);
    }

    public boolean deleteStudent(int id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return false;
        }

        studentRepository.delete(student);
        return true;
    }
}
