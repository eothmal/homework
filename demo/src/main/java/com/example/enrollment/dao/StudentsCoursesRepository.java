package com.example.enrollment.dao;

import com.example.enrollment.domain.StudentsCoursesMapping;
import org.springframework.data.repository.CrudRepository;


public interface StudentsCoursesRepository extends CrudRepository <StudentsCoursesMapping, Integer> {
//
//    public List<StudentsCoursesMapping> findByCourseId(String courseId);
//    public List<StudentsCoursesMapping> findByStudentId(String studentId);
}
