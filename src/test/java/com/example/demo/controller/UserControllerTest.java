package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void usersEndpoint() throws Exception {
        //TODO: продолжить этот тест
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getUsersEndpoint() throws Exception {
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
        // TODO: посмотреть этот тест
        doThrow(RuntimeException.class).when(userService).findAll();
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}