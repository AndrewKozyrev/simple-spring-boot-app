package com.example.demo.controller;

import com.example.demo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveUser(@RequestBody User user) {

        System.out.println(user);

        return "saved";
    }
}
