package com.springboot.todo.ToDoList.dao;

import com.springboot.todo.ToDoList.model.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User addUser(User user);

    List<User> getAllUsers(int page, int size, String sortBy);

    Optional<User> getUserById(ObjectId id);

    void updateUser(ObjectId id, User updatedUser);

    void deleteUser(ObjectId id);

    Optional<User> findByEmail(String email);
}
