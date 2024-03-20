package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    //   @EventListener(ApplicationStartedEvent.class)
    public void loadUsers() {
        log.info("START loadUsers");

        User user = new User();
        user.setName("Marie");
        user.setSurname("Botnar");
        user.setAge(122);

        userRepository.saveUser(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Удаление пользователя с ID: {}", id);
        try {
            if (userRepository.findUserById(id).isPresent()) {
                userRepository.deleteUser(id);
                logger.info("Пользователь с ID {} успешно удален", id);
                return true;
            } else {
                logger.warn("Пользователь с ID {} не найден", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Ошибка при удалении пользователя с ID {}", id, e);
            return false;
        }
    }
}
