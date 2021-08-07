package com.sda.student_nodb;

import com.sda.student_nodb.model.Student;
import com.sda.student_nodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class StudentNodbApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StudentNodbApplication.class, args);
    }

    @Autowired
    private StudentService service;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option = getOption(scanner);

        while (option >= 1 && option <= 4) {

            switch (option) {
                case 1:
                    register(scanner, service);
                    break;
                case 2:
                    findById(scanner, service);
                    break;
                case 3:
                    findAll(service);
                    break;
                case 4:
                    deleteById(scanner, service);
                    break;
            }

            option = getOption(scanner);
        }
    }

    private int getOption(Scanner scanner) {
        System.out.println("1. Enter 1 to register\n" +
                "2. Enter 2 to findById\n" +
                "3. Enter 3 to findAll \n" +
                "4. Enter 4 to deleteById\n");

        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private static void register(Scanner scanner, StudentService service) {
        System.out.println("Enter your first student name:");
        String name = scanner.nextLine();
        System.out.println("Enter your first student email:");
        String email = scanner.nextLine();
        System.out.println("Enter your first student gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter your first student address:");
        String address = scanner.nextLine();
        System.out.println("Enter your first student phoneNumber:");
        String phoneNumber = scanner.nextLine();


        var student = Student.builder()
                .name(name)
                .email(email)
                .gender(gender)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        var savedStudent = service.registerStudent(student);
        System.out.println(savedStudent);
    }

    private static void findById(Scanner scanner, StudentService service) {
        System.out.println("Enter student id:");
        long id = scanner.nextLong();
        scanner.nextLine();

        var savedStudent = service.getById(id);
        System.out.println(savedStudent);
    }

    private static void findAll(StudentService service) {
        System.out.println("All students records are:");

        var allStudents = service.getAll();
        for (var student : allStudents) {
            System.out.println(student);
        }
    }

    private static void deleteById(Scanner scanner, StudentService service) {
        System.out.println("Enter student id:");
        long id = scanner.nextLong();
        scanner.nextLine();

        service.removeStudent(id);
        System.out.println("Student successfully removed");
    }
}
