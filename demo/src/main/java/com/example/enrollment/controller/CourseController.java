package com.example.enrollment.controller;

import com.example.enrollment.dao.CourseRepository;
import com.example.enrollment.dao.StudentRepository;
import com.example.enrollment.dao.StudentsCoursesRepository;
import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.domain.StudentsCoursesMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentsCoursesRepository studentsCoursesRepository;

    @GetMapping("/students")
    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/courses")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentDetails(@PathVariable String studentId) {
        return studentRepository.findById(studentId).get();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseDetails(@PathVariable String courseId) {
        return courseRepository.findById(courseId).get();
    }

    @PostMapping("/students")
    public String createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return "success";
    }

    @PostMapping("/courses")
    public String createCourse(@RequestBody Course course) {
        courseRepository.save(course);
        return "success";
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> retrieveCoursesForAStudent(@PathVariable String studentId) {
        List<StudentsCoursesMapping> studentsCoursesMappings = studentsCoursesRepository.findByStudentId(studentId);

        List<Course> courses = new ArrayList<>();
        for (StudentsCoursesMapping courseMapping : studentsCoursesMappings) {
            Course course = courseRepository.findById(courseMapping.getCourseId()).get();
            courses.add(course);
        }
        return courses;
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> retrieveStudentsForACourse(@PathVariable String courseId) {
        List<StudentsCoursesMapping> studentsCoursesMappings = studentsCoursesRepository.findByCourseId(courseId);

        List<Student> students = new ArrayList<>();
        for (StudentsCoursesMapping studentMapping : studentsCoursesMappings) {
            Student student = studentRepository.findById(studentMapping.getStudentId()).get();
            students.add(student);
        }
        return students;
    }

    @PostMapping("/enroll")
    public String enroll(@RequestBody Map<String, String> json) {
        StudentsCoursesMapping studentsCoursesMapping =
                new StudentsCoursesMapping(Integer.valueOf(json.get("id")), json.get("studentId"), json.get("courseId"));
        StudentsCoursesMapping ret = studentsCoursesRepository.save(studentsCoursesMapping);
        return ret != null ? "Success" : "Failure";
    }

    @GetMapping("/enroll")
    public Iterable<StudentsCoursesMapping> getStudentsCoursesMapping() {
        return studentsCoursesRepository.findAll();
    }

}
