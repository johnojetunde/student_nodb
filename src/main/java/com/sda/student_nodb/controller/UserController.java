package com.sda.student_nodb.controller;

import com.sda.student_nodb.model.User;
import com.sda.student_nodb.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/add")
    public String addUser(User user) {
        return "user-registration";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user-registration";
        }

        customUserDetailsService.registerUser(user);

        return "redirect:/login";
    }
}
