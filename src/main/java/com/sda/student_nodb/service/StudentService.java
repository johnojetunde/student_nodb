package com.sda.student_nodb.service;

import com.sda.student_nodb.model.Student;
import com.sda.student_nodb.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public Student registerStudent(Student student) {
        var existingStudent = repository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            System.out.printf("\u001B[31m" + " Student with email %s \u001B[0m %n", student.getEmail());
        }

        return repository.save(student);
    }

    public void removeStudent(Long id) {
        repository.delete(id);
    }

    public List<Student> getAllAndFilter(String name, String phoneNumber) {
        return repository.findAll().stream()
                .filter(s -> filterByMatchingFields(s, name, phoneNumber))
                .collect(Collectors.toList());
    }

    private boolean filterByMatchingFields(Student student, String name, String phoneNumber) {
//        boolean studentNameMatching = true;
//        boolean studentPhoneNumberMatching = true;
//        if(name != null){
//            studentNameMatching = student.getName().equalsIgnoreCase(name);
//        }
//
//        if(phoneNumber != null){
//            studentPhoneNumberMatching = student.getPhoneNumber().equalsIgnoreCase(phoneNumber);
//        }
//
//        return studentNameMatching && studentPhoneNumberMatching;

        //
        boolean matchingName = Optional.ofNullable(name)
                .map(n -> student.getName().equalsIgnoreCase(n))
                .orElse(true);

        boolean matchingPhoneNumber = Optional.ofNullable(phoneNumber)
                .map(n -> student.getPhoneNumber().equalsIgnoreCase(n))
                .orElse(true);

        return matchingName && matchingPhoneNumber;
    }

    public Collection<Student> getAll() {
        return repository.findAll();
    }

    public Student update(Student student, Long id) {
        return repository.update(student, id);
    }

    public Student getById(Long id) {
        var studentOptional = repository.findById(id);

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        }

        System.out.printf("\u001B[31m" + " Student with id %d  does not exist \u001B[0m %n", id);
        return null;
    }
}
