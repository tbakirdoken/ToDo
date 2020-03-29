package com.springboot.todo.ToDoList.dao;

import com.springboot.todo.ToDoList.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    User addUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    void updateUser(String id, User updatedUser);

    void deleteUser(String id);
}
