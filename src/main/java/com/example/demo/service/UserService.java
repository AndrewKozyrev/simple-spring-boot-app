package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void loadUsers() {
        log.info("START loadUsers");

        User user = new User();
        user.setName("Andrew");
        user.setSurname("Kozyrev");
        user.setAge(22);

        User user2 = new User();
        user2.setName("Leon");
        user2.setSurname("Kennedy");
        user2.setAge(11);

        userRepository.saveUser(user);
        userRepository.saveUser(user2);
    }
}
