package dev.v3ktor.model.repository;

import dev.v3ktor.model.entity.User;

import java.util.List;

public interface UserRepository {

    // C -> Create
    void save(User user);

    // R -> Read
    User findById(int id);
    User findByUsername(String username);
    List<User> findAll();

    // U -> Update
    User update(User user);

    // D -> Delete
    void delete(User user);

}
