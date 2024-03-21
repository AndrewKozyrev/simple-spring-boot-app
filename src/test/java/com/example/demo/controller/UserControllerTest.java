package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    //TODO: написать тесты для всех методов контроллера

    @Test
    void testGetUsersEndpoint() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "John"));
        userList.add(new User(2, "Alice"));

        Mockito.when(userService.findAll()).thenReturn(userList);

        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/users"))
                    .andExpect(status().isOk());
        });

        Mockito.verify(userService, times(1)).findAll();
    }

    @Test
    void testShowUserFormEndpoint() {
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/user-form"))
                    .andExpect(status().isOk());
        });
    }

    @Test
    void testSaveUserEndpoint() {
        User user = new User(1, "Test User");

        assertDoesNotThrow(() -> {
            mockMvc.perform(post("/save")
                            .param("id", String.valueOf(user.getId()))
                            .param("name", user.getName()))
                    .andExpect(status().is3xxRedirection());
        });

        Mockito.verify(userService, times(1)).save(user);
    }

    @Test
    void testDeleteUserEndpoint() {
        Long userId = 1L;

        assertDoesNotThrow(() -> {
            mockMvc.perform(delete("/users/{id}", userId))
                    .andExpect(status().isOk());
        });

        Mockito.verify(userService, times(1)).delete(userId);
    }

    @Test
    void testEditUserEndpoint() {
        Long userId = 1L;
        User updatedUser = new User(1, "Updated User");

        assertDoesNotThrow(() -> {
            mockMvc.perform(put("/users/{id}", userId)
                            .param("id", String.valueOf(updatedUser.getId()))
                            .param("name", updatedUser.getName()))
                    .andExpect(status().is3xxRedirection());
        });

        Mockito.verify(userService, times(1)).update(updatedUser);
    }

}