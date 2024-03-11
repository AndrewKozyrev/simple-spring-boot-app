package com.example.demo.repository;

import com.example.demo.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findUserById(Long id);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
}
