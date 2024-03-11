package com.example.demo.repository;

import com.example.demo.model.User;
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
        String sql = "SELECT * FROM person";

        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        String sql = "SELECT * FROM person WHERE id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, id));
    }

    @Override
    public User saveUser(User user) {
        String sql = "INSERT INTO person (id, name, surname, age) VALUES (?, ?, ?, ?)";
        user.setId(System.currentTimeMillis());
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getSurname(), user.getAge());
        return user;
    }

    @Override
    public User updateUser(User user) {
        String sql = "UPDATE person SET name = ?, surname = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(), user.getId());
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
