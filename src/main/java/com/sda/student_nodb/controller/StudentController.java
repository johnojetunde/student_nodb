package com.sda.student_nodb.controller;

import com.sda.student_nodb.model.Student;
import com.sda.student_nodb.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student create(@RequestBody Student student) {
        return service.registerStudent(student);
    }

    // -  /students -  GET
    //students?name=john&address=address
    @GetMapping
    public Collection<Student> getAll(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
        return service.getAllAndFilter(name, phoneNumber);
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

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return service.update(student, id);
    }
}
