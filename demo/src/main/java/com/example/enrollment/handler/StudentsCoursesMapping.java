package com.example.enrollment.handler;

public class StudentsCoursesMapping {
    private String studentId;
    private String courseId;

    public StudentsCoursesMapping() {
    }

    public StudentsCoursesMapping(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
