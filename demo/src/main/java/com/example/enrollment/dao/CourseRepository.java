package com.example.enrollment.dao;

import com.example.enrollment.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {
}
