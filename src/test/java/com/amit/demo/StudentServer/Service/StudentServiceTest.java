package com.amit.demo.StudentServer.Service;

import com.amit.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.amit.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.amit.demo.StudentServer.Entity.Student;
import com.amit.demo.StudentServer.Exception.DuplicateEmailException;
import com.amit.demo.StudentServer.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Rolls back database changes automatically after each test
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private CreateStudentRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new CreateStudentRequestDTO();
        requestDTO.setName("Amit Tiwari");
        requestDTO.setAge(22);
        requestDTO.setDepartment("Computer Science");
        requestDTO.setEmail("amit@example.com");
    }

    // ==========================================
    // CREATE TESTS
    // ==========================================
    @Nested
    @DisplayName("Create Student Tests")
    class CreateStudentTests {

        @Test
        @DisplayName("Should successfully create student and persist to database")
        void createStudent_Success() {
            CreateStudentResponseDTO response = studentService.createStudent(requestDTO);

            assertNotNull(response);
            assertTrue(response.getId() > 0);
            assertEquals("Amit Tiwari", response.getName());
            assertEquals("amit@example.com", response.getEmail());

            // Verify database persistence
            assertTrue(studentRepository.findById(response.getId()).isPresent());
        }

        @Test
        @DisplayName("Should throw exception when request DTO is null")
        void createStudent_NullRequest_ThrowsException() {
            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> studentService.createStudent(null)
            );

            assertEquals("Request body cannot be null", exception.getMessage());
        }
    }

    // ==========================================
    // READ TESTS
    // ==========================================
    @Nested
    @DisplayName("Get Student By ID Tests")
    class GetStudentByIdTests {

        @Test
        @DisplayName("Should fetch existing student by ID")
        void getStudentById_Success() {
            CreateStudentResponseDTO created = studentService.createStudent(requestDTO);

            CreateStudentResponseDTO found = studentService.getStudentById(created.getId());

            assertNotNull(found);
            assertEquals(created.getId(), found.getId());
            assertEquals("Amit Tiwari", found.getName());
        }

        @Test
        @DisplayName("Should throw exception when student ID does not exist")
        void getStudentById_NotFound_ThrowsException() {
            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> studentService.getStudentById(99999)
            );

            assertEquals("Student not found", exception.getMessage());
        }
    }

    // ==========================================
    // PUT TESTS
    // ==========================================
    @Nested
    @DisplayName("PUT Student Tests")
    class PutStudentTests {

        @Test
        @DisplayName("Should update all fields of existing student")
        void putStudent_Success() {
            CreateStudentResponseDTO created = studentService.createStudent(requestDTO);

            CreateStudentRequestDTO updateDTO = new CreateStudentRequestDTO();
            updateDTO.setName("Amit Kumar Tiwari");
            updateDTO.setAge(23);
            updateDTO.setDepartment("Information Technology");
            updateDTO.setEmail("amit.updated@example.com");

            CreateStudentResponseDTO updated = studentService.putStudent(created.getId(), updateDTO);

            assertEquals("Amit Kumar Tiwari", updated.getName());
            assertEquals(23, updated.getAge());
            assertEquals("Information Technology", updated.getDepartment());
            assertEquals("amit.updated@example.com", updated.getEmail());
        }

        @Test
        @DisplayName("Should throw DuplicateEmailException if updated email belongs to another student")
        void putStudent_DuplicateEmail_ThrowsException() {
            // Student 1
            studentService.createStudent(requestDTO);

            // Student 2
            CreateStudentRequestDTO secondRequest = new CreateStudentRequestDTO();
            secondRequest.setName("Rahul Sharma");
            secondRequest.setAge(24);
            secondRequest.setDepartment("Mechanical");
            secondRequest.setEmail("rahul@example.com");
            CreateStudentResponseDTO student2 = studentService.createStudent(secondRequest);

            // Attempt to update Student 2 with Student 1's email ("amit@example.com")
            secondRequest.setEmail("amit@example.com");

            assertThrows(
                    DuplicateEmailException.class,
                    () -> studentService.putStudent(student2.getId(), secondRequest)
            );
        }
    }

    // ==========================================
    // PATCH TESTS
    // ==========================================
    @Nested
    @DisplayName("PATCH Student Tests")
    class PatchStudentTests {

        @Test
        @DisplayName("Should update only non-null/valid fields")
        void patchStudent_PartialUpdate_Success() {
            CreateStudentResponseDTO created = studentService.createStudent(requestDTO);

            CreateStudentRequestDTO patchDTO = new CreateStudentRequestDTO();
            patchDTO.setName("Amit T."); // Updating name only

            CreateStudentResponseDTO patched = studentService.patchStudent(created.getId(), patchDTO);

            assertEquals("Amit T.", patched.getName());
            assertEquals(22, patched.getAge()); // Original age preserved
            assertEquals("amit@example.com", patched.getEmail()); // Original email preserved
        }
    }

    // ==========================================
    // DELETE TESTS
    // ==========================================
    @Nested
    @DisplayName("DELETE Student Tests")
    class DeleteStudentTests {

        @Test
        @DisplayName("Should delete existing student")
        void deleteStudent_Success() {
            CreateStudentResponseDTO created = studentService.createStudent(requestDTO);

            boolean result = studentService.deleteStudent(created.getId());

            assertTrue(result);
            assertFalse(studentRepository.findById(created.getId()).isPresent());
        }

        @Test
        @DisplayName("Should throw exception when trying to delete non-existent student")
        void deleteStudent_NotFound_ThrowsException() {
            assertThrows(
                    RuntimeException.class,
                    () -> studentService.deleteStudent(99999)
            );
        }
    }
}