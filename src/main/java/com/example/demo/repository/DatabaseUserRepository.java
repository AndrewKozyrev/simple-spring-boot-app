package com.example.demo.repository;

import com.example.demo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DatabaseUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setName(rs.getString(User.Fields.name));
        user.setSurname(rs.getString(User.Fields.surname));
        user.setAge(rs.getInt(User.Fields.age));
        user.setId(rs.getLong(User.Fields.id));

        return user;
    };

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";

        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public User saveUser() {
        return null;
    }

    @Override
    public User updateUser() {
        return null;
    }

    @Override
    public void deleteUser() {

    }
}
