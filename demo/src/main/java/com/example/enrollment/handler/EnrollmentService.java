package com.example.enrollment.handler;

import com.example.enrollment.model.Course;
import com.example.enrollment.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnrollmentService {
    private static  List<Student> students = new ArrayList<> ();
    private static List<Course> courses =  new ArrayList<>();
    private static List<StudentsCoursesMapping> studentsCoursesMappings = new ArrayList<>();
    static {
       students.add(new Student("Ikhlas","Alhamd","iklm.ahmed@outmail.com","1"));
       students.add(new Student("Mohammed","Toha","mtoha1000@outmail.com","2"));
       students.add(new Student("Anees", "Belum","roshhold@outmail.com","3"));
       students.add(new Student("Herman", "Mutar","hmutar@outmail.com","4"));

       courses.add(new Course("Java Spring boot", "Rahman","JSB102"));
       courses.add(new Course("Amazon Web Service","EPIC","AWS101"));
       courses.add(new Course("Angular Programming","Plano","ANG101"));

       studentsCoursesMappings.add(new StudentsCoursesMapping("1","JSB102"));
        studentsCoursesMappings.add(new StudentsCoursesMapping("4","JSB102"));
        studentsCoursesMappings.add(new StudentsCoursesMapping("4","ANG101"));
    }

    public Student retrieveStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public Course retrieveCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public boolean enrollInACourse(String studentId, String courseId) {
        boolean ret = false;
        Course course = retrieveCourse(courseId);
        if (course != null)
        {
            Student student = retrieveStudent(studentId);
            if (student != null) {
                studentsCoursesMappings.add(new StudentsCoursesMapping(studentId,courseId));
                ret = true;
            }
        }
        return ret;
    }

    public List<Course> retrieveCoursesForAStudent(String studentId) {
        Student student = retrieveStudent(studentId);
        List<Course> list = new ArrayList<>();
        if (student != null) {
            for (StudentsCoursesMapping obj : studentsCoursesMappings)
                if (obj.getStudentId().equals(studentId))
                  list.add(retrieveCourse(obj.getCourseId()));
        }
        return list;
    }

    public List<Student> retrieveStudentsForACourse(String courseId) {
        Course course = retrieveCourse(courseId);
        List<Student> list = new ArrayList<>();
        if (course != null) {
            for (StudentsCoursesMapping obj : studentsCoursesMappings)
                if (obj.getCourseId().equals(courseId))
                    list.add(retrieveStudent(obj.getStudentId()));
        }
        return list;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public static List<StudentsCoursesMapping> getStudentsCoursesMappings() {
        return studentsCoursesMappings;
    }
}
