package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String users() {
        return "users.html";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/user-form")
    public String showUserForm() {
        return "user-form.html";
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE)
    public String saveUser(@Valid @RequestBody User user) {
        userService.save(user);

        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.debug("calling DELETE method with id={}", id);
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    //TODO: написать метод для обновления пользователя "editUser"
}
