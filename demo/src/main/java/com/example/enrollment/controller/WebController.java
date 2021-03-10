package com.example.enrollment.controller;

import com.example.enrollment.domain.Course;
import com.example.enrollment.domain.Student;
import com.example.enrollment.handler.EnrollmentServiceDb;
import com.example.enrollment.handler.MapValidationErrorService;
import com.example.enrollment.handler.StudentsCoursesMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController implements WebMvcConfigurer{

    @Autowired
    private EnrollmentServiceDb enrollmentService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @RequestMapping("/start")
    public String getStart(Model model){
        model.addAttribute("studentData", new Student());
        model.addAttribute("courseData", new Course());
        model.addAttribute("studentsCoursesMapping", new StudentsCoursesMapping());
        return "index1";
    }

    @RequestMapping("/students")
    public String getStudents(Model model){
        List<Student> students = enrollmentService.getStudents();
        model.addAttribute("students", students);
        return "students_list";
    }

    @RequestMapping("/courses")
    public String getCourses(Model model){
        List<Course> courses = enrollmentService.getCourses();
        model.addAttribute("courses", courses);
        return "courses_list";
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
//    public String createStudent(Student student, Model model) {
    public String createStudent(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
//                return "form";
            return "student_failure";
        }
        Student  student1 = enrollmentService.addStudent(student);
//        model.addAttribute("student", student);
        return "student_success";
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
//    public String createCourse(@Valid Course course, Model model, BindingResult result) {
    public String createCourse(@Valid Course course, BindingResult result) {
//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
//        if(errorMap !=null) {
//            errorMap.toString();
//        model.addAttribute("errorMap", errorMap);
            if (result.hasErrors()) {
//                return "form";
            return "course_failure";
            }

        Course course1 = enrollmentService.addCourse(course);
//        model.addAttribute("course", course1);
//        ResponseEntity<Course> responseEntity = new ResponseEntity<>(course1, HttpStatus.CREATED);
//        model.addAttribute("responseEntity", responseEntity);
        return "course_success";
    }

    @RequestMapping("/students/{studentId}")
    public String getStudentDetails(@PathVariable String studentId, RedirectAttributes attr){
        Student student = enrollmentService.retrieveStudent(studentId);
        attr.addFlashAttribute("student", student);
        return "redirect:/web/student/display";
    }

    @RequestMapping("/student/display")
    public String studentDisplay(Student student){
        return "student_detail";
    }

    @RequestMapping("/courses/{courseId}")
    public String getCourseDetails(@PathVariable String courseId, RedirectAttributes attr){
        Course course = enrollmentService.retrieveCourse(courseId);
        attr.addFlashAttribute("course", course);
        return "redirect:/web/course/display";
    }

    @RequestMapping("/course/display")
    public String courseDisplay(Course course){
        return "course_detail";
    }

    /* */
//    @RequestMapping("/students/{studentId}/courses")
    @RequestMapping("/students/courses/{studentId}")
    public String retrieveCoursesForAStudent(@PathVariable String studentId, Model model)
    {
        List<Course> courses = enrollmentService.retrieveCoursesForAStudent(studentId);
        model.addAttribute("courses", courses);
        model.addAttribute("studentId", studentId);
        return "courses_list";
    }

//    @GetMapping("/courses/{courseId}/students")
    @GetMapping("/courses/students/{courseId}")
    public String retrieveStudentsForACourse(@PathVariable String courseId, Model model)
    {
        List<Student> students = enrollmentService.retrieveStudentsForACourse(courseId);
        model.addAttribute("students", students);
        model.addAttribute("courseId", courseId);
        return "students_list";
    }

    @PostMapping("/enroll")
    public String enroll(StudentsCoursesMapping studentsCoursesMapping, Model model) {
        boolean ret = enrollmentService.enrollInACourse(studentsCoursesMapping.getStudentId(), studentsCoursesMapping.getCourseId());
        model.addAttribute("studentsCoursesMapping",  studentsCoursesMapping);
        return ret ? "enroll_success": "Failure";
    }

    @GetMapping("/enroll")
    public String getStudentsCoursesMapping(Model model) {
        List<StudentsCoursesMapping> studentsCoursesMappings = enrollmentService.getStudentsCoursesMappings();
        model.addAttribute("studentsCoursesMappings",studentsCoursesMappings );
        return "enrollment";
    }
}
