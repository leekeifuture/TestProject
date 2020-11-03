package com.example.TestProject.controller;

import com.example.TestProject.dto.User;
import com.example.TestProject.service.IUserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
@AllArgsConstructor
@Api(description = "Registering users")
public class UserController {

    private final IUserService userService;

    @PostMapping("/singUp")
    public void signUp(@RequestBody User user) {
        userService.signUp(user);
    }
}
