package com.example.enrollment.controller;

import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.domain.StudentsCoursesMapping;
import com.example.enrollment.handler.EnrollmentServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CourseEnrollmentControllerDb {

@Autowired private EnrollmentServiceDb enrollmentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return enrollmentService.getStudents();
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return enrollmentService.getCourses();
    }

    @GetMapping("/students/{studentId}")
        public Student getStudentDetails(@PathVariable String studentId) {
        return  enrollmentService.retrieveStudent(studentId);
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseDetails(@PathVariable String courseId) {
        return enrollmentService.retrieveCourse(courseId);
    }

    @PostMapping("/students")
    public String createStudent(@RequestBody Student student) {
        boolean ret = enrollmentService.addStudent(student);
        return ret ? "Success": "Failure";
    }

    @PostMapping("/courses")
    public String createCourse(@RequestBody Course course) {
        boolean ret = enrollmentService.addCourse(course);
        return ret ? "Success": "Failure";
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> retrieveCoursesForAStudent(@PathVariable String studentId)
    {
        return enrollmentService.retrieveCoursesForAStudent(studentId);
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> retrieveStudentsForACourse(@PathVariable String courseId)
    {
        return enrollmentService.retrieveStudentsForACourse(courseId);
    }
    @PostMapping("/enroll")
    public String enroll(@RequestBody Map<String, String> json) {
        String studentId = json.get("studentId");
        String courseId = json.get("courseId");
        boolean ret = enrollmentService.enrollInACourse(studentId, courseId);
        return ret ? "Success": "Failure";
    }
    @GetMapping("/enroll")
    public List<StudentsCoursesMapping> getStudentsCoursesMapping() {
        return enrollmentService.getStudentsCoursesMappings();
    }

}
