package com.example.enrollment.dao;

import java.util.List;

import com.example.enrollment.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

}
