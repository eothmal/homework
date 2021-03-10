package com.example.enrollment.controller;

import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.handler.EnrollmentServiceDb;
import com.example.enrollment.handler.StudentsCoursesMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private EnrollmentServiceDb enrollmentService;

    @RequestMapping("/all")
    public String getIndex(Model model){
        model.addAttribute("studentData", new Student());
        model.addAttribute("courseData", new Course());
        model.addAttribute("studentsCoursesMapping", new StudentsCoursesMapping());
        return "base";
    }

    @RequestMapping("/student")
    public String getStudentHome(Model model){
        model.addAttribute("studentData", new Student());
        return "student";
    }

    @RequestMapping("/course")
    public String getCourseHome(Model model){
        model.addAttribute("courseData", new Course());
        return "course";
    }

    @RequestMapping("/enroll")
    public String getEnrollHome(Model model){
        model.addAttribute("studentsCoursesMapping", new StudentsCoursesMapping());
        return "enroll";
    }

}
