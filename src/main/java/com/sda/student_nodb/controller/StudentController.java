package com.sda.student_nodb.controller;

import com.sda.student_nodb.model.Student;
import com.sda.student_nodb.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/students", produces = APPLICATION_JSON_VALUE)
public class StudentController {
    private final StudentService service;
    //CRUD

    // -  /students - POST
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    @RequestMapping(method = RequestMethod.POST)
    public Student create(@RequestBody Student student) {
        return service.registerStudent(student);
    }

    // -  /students -  GET
    @GetMapping
    public Collection<Student> getAll() {
        return service.getAll();
    }

    // -  /students/1234 -  GET
    @GetMapping("/{id}")
    //@RequestMapping(method = GET, value = "/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    // - /students/123 -  DELETE
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        service.removeStudent(id);
        return "Student successfully deleted";
    }
}
