package com.example.demo.controller;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveUser(@Valid @RequestBody User user) {

        // TODO: сохранить пользователя в БД, вместо вывода в консоль. Для этого нужно будет использовать UserService метод saveUser
        System.out.println(user);

        return "saved";
    }
}
