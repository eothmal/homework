package com.example.enrollment.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentsCoursesMapping {
    @Id
    private int id;
    private String studentId;
    private String courseId;

    public StudentsCoursesMapping() {
    }

    public StudentsCoursesMapping(int id, String studentId, String courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

}