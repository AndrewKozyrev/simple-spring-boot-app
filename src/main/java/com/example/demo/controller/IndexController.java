package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE)
    public String saveUser(@Valid @RequestBody User user) {

        userService.save(user);

        return "redirect:/index";
    }

    /*@DeleteMapping("/delete/{id}")
    public String deleteUser()*/
    //TODO http://localhost:8080/delete/1         ---        должен удалять пользователя с индексом 1
    // контроллер должен принимать переменную пути @PathVariable id
    // контроллер должен вызывать сервис userService.delete([идентификатор])
    // контроллер должен направлять на путь /index
}
