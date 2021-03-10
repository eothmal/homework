package com.example.enrollment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@JsonIgnoreProperties({"students"})
public class Course {
    @NotBlank(message = "Course name is required")
    private String name;
    @NotBlank(message = "Course OfferBy is required")
    private String offeredBy;
    @Id
    @NonNull
    @Size(min=6,max = 6)
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
