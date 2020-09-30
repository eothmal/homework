package com.example.enrollment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"students"})
public class Course {
    private String name;
    private String offeredBy;
    @Id
    private String courseId;

    public Course() {
    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "students_courses" ,
    joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public Course(String name, String offeredBy, String courseId) {
        super();
        this.name = name;
        this.offeredBy = offeredBy;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public String getOfferedBy() {
        return offeredBy;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOfferedBy(String offeredBy) {
        this.offeredBy = offeredBy;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
