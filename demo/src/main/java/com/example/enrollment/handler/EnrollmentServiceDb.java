package com.example.enrollment.handler;

import com.example.enrollment.dao.CourseRepository;
import com.example.enrollment.dao.StudentRepository;
import com.example.enrollment.dao.StudentsCoursesRepository;
import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.domain.StudentsCoursesMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class EnrollmentServiceDb {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentsCoursesRepository studentsCoursesRepository;

    public Student retrieveStudent(String studentId) {
        return studentRepository.findById(studentId).get();
    }

    public Course retrieveCourse(String courseId) {
        return courseRepository.findById(courseId).get();
    }

    public boolean enrollInACourse(String id,String studentId, String courseId) {
        StudentsCoursesMapping studentsCoursesMapping =
                new StudentsCoursesMapping(Integer.valueOf(id), studentId, courseId);
        StudentsCoursesMapping ret = studentsCoursesRepository.save(studentsCoursesMapping);
        return ret != null ? true : false;
    }

    public List<Course> retrieveCoursesForAStudent(String studentId) {
        List<StudentsCoursesMapping> studentsCoursesMappings = studentsCoursesRepository.findByStudentId(studentId);

        List<Course> courses = new ArrayList<>();
        for (StudentsCoursesMapping courseMapping : studentsCoursesMappings) {
            Course course = courseRepository.findById(courseMapping.getCourseId()).get();
            courses.add(course);
        }
        return courses;
    }

    public List<Student> retrieveStudentsForACourse(String courseId) {
        List<StudentsCoursesMapping> studentsCoursesMappings = studentsCoursesRepository.findByCourseId(courseId);

        List<Student> students = new ArrayList<>();
        for (StudentsCoursesMapping studentMapping : studentsCoursesMappings) {
            Student student = studentRepository.findById(studentMapping.getStudentId()).get();
            students.add(student);
        }
        return students;
    }

    public List<Course> getCourses() {
        return StreamSupport.stream(courseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public List<Student> getStudents() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public boolean addCourse(Course course) {
        Course ret = courseRepository.save(course);
        return ret !=null ? true: false;
    }

    public boolean addStudent(Student student) {
        Student ret = studentRepository.save(student);;
        return ret !=null ? true: false;
    }

    public List<StudentsCoursesMapping> getStudentsCoursesMappings() {
        return StreamSupport.stream(studentsCoursesRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
