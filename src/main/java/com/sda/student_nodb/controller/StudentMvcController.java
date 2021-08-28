package com.sda.student_nodb.controller;

import com.sda.student_nodb.model.Student;
import com.sda.student_nodb.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class StudentMvcController {
    private final StudentService service;

    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("helloMsg", "Hello, Welcome to Spring MVC Thymeleaf class");
        var students = service.getAll();
        model.addAttribute("students", students);
        return "welcome";
    }

    @GetMapping("/mvc/students/add")
    public String addStudent(Student student) {
        return "add-student";
    }

    @PostMapping("/mvc/students")
    public String saveStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        model.addAttribute("helloMsg", "Student Registration Successful");
        service.registerStudent(student);
        return "redirect:/";
    }

    @GetMapping("/mvc/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        service.removeStudent(id);
        return "redirect:/";
    }

    @GetMapping("/mvc/students/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        var student = service.getById(id);
        model.addAttribute("student", student);

        return "edit-student";
    }

    @PostMapping("/mvc/students/edit/{id}")
    public String saveUpdatedStudentInfo(@PathVariable("id") Long id,
                                         @Valid Student student,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return "edit-student";
        }
        service.update(student, id);
        return "redirect:/";
    }
}
