package com.example.enrollment.handler;

import com.example.enrollment.dao.CourseRepository;
import com.example.enrollment.dao.StudentRepository;
import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
class EnrollmentServiceDbTest {

    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private EnrollmentServiceDb enrollmentService;


    @Test
    void retrieveStudentTest() {
        String studentId = "1";
        Student student = new Student("first", "last", "flast@mail.com", "1");
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        assertEquals(student, enrollmentService.retrieveStudent(studentId));
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void retrieveCourseTest() {
        String courseId = "PY101";
        Course course = new Course("Python", "Irving", "PY101");
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
    }

    @Test
    void enrollInACourseTest() {
        String id = "1";
        String courseId = "PY101";
        String studentId = "1";
        Course course = new Course();
        List<Student> students = new ArrayList<>();
        course.setStudents(students);
        List<Course> courses = new ArrayList<>();
        Student student = new Student();
        student.setCourses(courses);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        assertEquals(true, enrollmentService.enrollInACourse(studentId, courseId));
    }

    @Test
    void retrieveCoursesForAStudentTest() {
        String studentId = "1";
        List<Course> courses = new ArrayList<>();
        Student student = new Student("first", "last", "flast@mail.com", "1");
        student.setCourses(courses);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        assertEquals(courses, enrollmentService.retrieveCoursesForAStudent(studentId));
    }

    @Test
    void retrieveStudentsForACourseTest() {
        String courseId = "PY101";
        Course course = new Course("Python", "Irving", courseId);
        List<Student> students = new ArrayList<>();
        course.setStudents(students);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        assertEquals(students, enrollmentService.retrieveStudentsForACourse(courseId));
    }

    @Test
    void getCoursesTest() {
        List<Course> courses = new ArrayList<>();
        Course course = new Course("Python", "Irving", "PY101");
        courses.add(course);
        when(courseRepository.findAll()).thenReturn(courses);
        assertEquals(courses, enrollmentService.getCourses());
    }

    @Test
    void getStudentsTest() {
        List<Student> students = new ArrayList<>();
        Student student = new Student("first", "last", "flast@mail.com", "1");
        students.add(student);
        when(studentRepository.findAll()).thenReturn(students);
        assertEquals(students, enrollmentService.getStudents());
    }

}