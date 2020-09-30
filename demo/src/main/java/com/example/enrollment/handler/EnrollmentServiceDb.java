package com.example.enrollment.handler;

import com.example.enrollment.dao.CourseRepository;
import com.example.enrollment.dao.StudentRepository;
import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
//import com.example.enrollment.domain.StudentsCoursesMapping;
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

    public Student retrieveStudent(String studentId) {
        return studentRepository.findById(studentId).get();
    }

    public Course retrieveCourse(String courseId) {
        return courseRepository.findById(courseId).get();
    }

    public boolean enrollInACourse(String studentId, String courseId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();

        List<Course> courses = student.getCourses();
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        student.setCourses(courses);

        Student ret1 = studentRepository.save(student);

        List<Student> students = course.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        course.setStudents(students);

        Course ret2 = courseRepository.save(course);

        return (ret1 != null && ret2 != null) ? true : false;
    }

    public List<Course> retrieveCoursesForAStudent(String studentId) {

        return studentRepository.findById(studentId).get().getCourses();
    }

    public List<Student> retrieveStudentsForACourse(String courseId) {

        return courseRepository.findById(courseId).get().getStudents();
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
        return ret != null ? true : false;
    }

    public boolean addStudent(Student student) {
        Student ret = studentRepository.save(student);
        ;
        return ret != null ? true : false;
    }

    public List<StudentsCoursesMapping> getStudentsCoursesMappings() {
        Iterable<Student> students = studentRepository.findAll();
        List<StudentsCoursesMapping> studentsCoursesMappings = new ArrayList<>();
        for (Student student : students) {
            for (Course course : student.getCourses()) {
                StudentsCoursesMapping studentsCoursesMapping =
                        new StudentsCoursesMapping(student.getStudentId(), course.getCourseId());
                studentsCoursesMappings.add(studentsCoursesMapping);
            }

        }
        return studentsCoursesMappings;
    }
}
