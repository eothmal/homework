package com.example.enrollment.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    private String name;
    private String offeredBy;
    @Id
    private String courseId;

    public Course() {
    }

    public Course(String name, String offeredBy, String courseId) {
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

}
