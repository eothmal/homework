package com.example.enrollment.dao;

import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.domain.StudentsCoursesMapping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentsCoursesRepository extends CrudRepository <StudentsCoursesMapping, Integer> {

    public List<StudentsCoursesMapping> findByCourseId(String courseId);
    public List<StudentsCoursesMapping> findByStudentId(String studentId);
}
