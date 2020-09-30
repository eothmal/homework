package com.example.enrollment.controller;

import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.handler.StudentsCoursesMapping;
import com.example.enrollment.handler.EnrollmentServiceDb;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseEnrollmentControllerDb.class)
class CourseEnrollmentControllerDbTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EnrollmentServiceDb enrollmentService;

    private Student student;
    private Course course;
    private StudentsCoursesMapping studentsCoursesMapping;

    private List<Student> students;
    private List<Course> courses;
    private List<StudentsCoursesMapping> studentsCoursesMappingMappings;

    @BeforeEach
    void setup() {

        students = new ArrayList<>();
        student = new Student("first", "last", "flast@mail.com", "1");
        students.add(student);

        courses = new ArrayList<>();
        course = new Course("Python", "Irving", "PY101");
        courses.add(course);

        studentsCoursesMappingMappings = new ArrayList<>();
        studentsCoursesMapping =
                new StudentsCoursesMapping("1", "PY101");
        studentsCoursesMappingMappings.add(studentsCoursesMapping);

    }


    @Test
    void getStudentsTest() throws Exception {
        when(enrollmentService.getStudents()).thenReturn(students);
        mockMvc.perform(get("/students")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", hasSize(1))))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].firstName").value("first"))
                .andExpect(jsonPath("$[*].lastName").value("last"))
                .andExpect(jsonPath("$[*].email").value("flast@mail.com"));
    }

    @Test
    void getCoursesTest() throws Exception {
        when(enrollmentService.getCourses()).thenReturn(courses);
        mockMvc.perform(get("/courses")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", hasSize(1))))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].name").value("Python"))
                .andExpect(jsonPath("$[*].offeredBy").value("Irving"))
                .andExpect(jsonPath("$[*].courseId").value("PY101"));
    }

    @Test
    void getStudentDetailsTest() throws Exception {
        when(enrollmentService.retrieveStudent("1")).thenReturn(student);
        mockMvc.perform(get("/students/{studentId}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId").value("1"))
                .andExpect(jsonPath("$.firstName").value("first"))
                .andExpect(jsonPath("$.lastName").value("last"))
                .andExpect(jsonPath("$.email").value("flast@mail.com"));
    }

    @Test
    void getCourseDetailsTest() throws Exception {
        when(enrollmentService.retrieveCourse("1")).thenReturn(course);
        mockMvc.perform(get("/courses/{courseId}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Python"))
                .andExpect(jsonPath("$.offeredBy").value("Irving"))
                .andExpect(jsonPath("$.courseId").value("PY101"));
    }

    @Test
    void createStudentTest() throws Exception {
        when(enrollmentService.addStudent(student)).thenReturn(true);
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(student)))
                .andExpect(status().isOk());
    }

    @Test
    void createCourseTest() throws Exception {
        when(enrollmentService.addCourse(course)).thenReturn(true);
        mockMvc.perform(post("/courses").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(course)))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveCoursesForAStudentTest() throws Exception {
        when(enrollmentService.retrieveCoursesForAStudent("1")).thenReturn(courses);
        mockMvc.perform(get("/students/{studentId}/courses", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Python"))
                .andExpect(jsonPath("$[0].offeredBy").value("Irving"))
                .andExpect(jsonPath("$[0].courseId").value("PY101"));
    }

    @Test
    void retrieveStudentsForACourseTest() throws Exception {
        when(enrollmentService.retrieveStudentsForACourse("PY101")).thenReturn(students);
        mockMvc.perform(get("/courses/{courseId}/students", "PY101")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].studentId").value("1"))
                .andExpect(jsonPath("$[0].firstName").value("first"))
                .andExpect(jsonPath("$[0].lastName").value("last"))
                .andExpect(jsonPath("$[0].email").value("flast@mail.com"));
    }

    @Test
    void enrollTest() throws Exception {
        when(enrollmentService.enrollInACourse("1","PY101")).thenReturn(true);
        mockMvc.perform(post("/enroll").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(studentsCoursesMapping)))
                .andExpect(status().isOk());
    }

    @Test
    void getStudentsCoursesMappingTest() throws Exception {
        when(enrollmentService.getStudentsCoursesMappings()).thenReturn(studentsCoursesMappingMappings);
        mockMvc.perform(get("/enroll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", hasSize(1))))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].studentId").value("1"))
                .andExpect(jsonPath("$[*].courseId").value("PY101"));
    }

    /*
     * converts a Java object into JSON representation
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}