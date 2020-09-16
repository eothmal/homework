package com.example.enrollment.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.enrollment.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnrollmentServiceTest {
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setup() {
        enrollmentService = new EnrollmentService();
    }
    @Test
    void verifyAddStudent() {
        assertEquals(4,enrollmentService.getStudents().size());
        Student student = new Student("Ikhlas","Ahmed","ike.ahmed@outlook.com","1");
        enrollmentService.addStudent(student);
        assertEquals(student.getFirstName(), enrollmentService.retrieveStudent("1").getFirstName());
        assertEquals(5,enrollmentService.getStudents().size());
    }
}
