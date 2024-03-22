package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void users_success() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("users.html"));
    }

    @Test
    void getUsers_success() throws Exception {
        var list = List.of(new User(11L, "user1", "surname1", 21), new User(12L, "user2", "surname2", 22));
        doReturn(list).when(userService).findAll();
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", hasItems("user1", "user2")))
                .andExpect(jsonPath("$[*].id", hasItems(11, 12)));
    }

    @Test
    void getUsers_exception() throws Exception {
        when(userService.findAll()).thenThrow(new RuntimeException("error"));
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}