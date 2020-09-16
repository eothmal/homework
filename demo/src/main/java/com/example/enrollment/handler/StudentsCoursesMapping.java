package com.example.enrollment.handler;

public class StudentsCoursesMapping {
    private String studentId;
    private String courseId;

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

}
