package com.example.enrollment.handler;

import com.example.enrollment.dao.CourseRepository;
import com.example.enrollment.dao.StudentRepository;
import com.example.enrollment.dao.StudentsCoursesRepository;
import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.domain.StudentsCoursesMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
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

//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
@SpringBootTest
class EnrollmentServiceDbTest {

    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentsCoursesRepository studentsCoursesRepository;

    @Autowired
    private EnrollmentServiceDb enrollmentService;

//    @BeforeEach
//    void setup() {
//        enrollmentService = new EnrollmentServiceDb();
//        Student student = new Student();
//    }

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
        StudentsCoursesMapping studentsCoursesMapping =
                new StudentsCoursesMapping(Integer.valueOf(id), studentId, courseId);
        when(studentsCoursesRepository.save(any(StudentsCoursesMapping.class))).thenReturn(studentsCoursesMapping);
        assertEquals(true, enrollmentService.enrollInACourse(id, studentId, courseId));
//        assertEquals(course, enrollmentService.retrieveCourse(courseId));
    }

    @Test
    void retrieveCoursesForAStudentTest() {
        String studentId = "1";
        StudentsCoursesMapping studentsCoursesMapping = new StudentsCoursesMapping(1, "1", "PY101");
        List<StudentsCoursesMapping> studentsCoursesMappings = new ArrayList<>();
        studentsCoursesMappings.add(studentsCoursesMapping);
        List<Course> courses = new ArrayList<>();
        Course course = new Course("Python", "Irving", "PY101");
        courses.add(course);
        when(studentsCoursesRepository.findByStudentId(studentId)).thenReturn(studentsCoursesMappings);
        when(courseRepository.findById(studentsCoursesMapping.getCourseId())).thenReturn(Optional.of(course));
        assertEquals(courses, enrollmentService.retrieveCoursesForAStudent(studentId));
    }

    @Test
    void retrieveStudentsForACourseTest() {
        String courseId = "PY101";
        StudentsCoursesMapping studentsCoursesMapping = new StudentsCoursesMapping(1, "1", "PY101");
        List<StudentsCoursesMapping> studentsCoursesMappings = new ArrayList<>();
        studentsCoursesMappings.add(studentsCoursesMapping);
        List<Student> students = new ArrayList<>();
        Student student = new Student("first", "last", "flast@mail.com", "1");
        students.add(student);
        when(studentsCoursesRepository.findByCourseId(courseId)).thenReturn(studentsCoursesMappings);
        when(studentRepository.findById(studentsCoursesMapping.getStudentId())).thenReturn(Optional.of(student));
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