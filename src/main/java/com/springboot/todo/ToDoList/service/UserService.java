package com.springboot.todo.ToDoList.service;

import com.mongodb.client.result.UpdateResult;
import com.springboot.todo.ToDoList.dao.UserDao;
import com.springboot.todo.ToDoList.dto.Response.UserResponse;
import com.springboot.todo.ToDoList.exception.UserNotFoundException;
import com.springboot.todo.ToDoList.mapper.UserMapper;
import com.springboot.todo.ToDoList.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("Users") UserDao userDao) {
        this.userDao = userDao;
    }

    public UserResponse addUser(User user) {
        User createdUser = userDao.addUser(user);
        return UserMapper.map.userToUserResponse(createdUser);
    }

    public List<UserResponse> getAllUsers(int page, int size, String sortBy) {
        return userDao.getAllUsers(page, size, sortBy)
                .stream()
                .map(UserMapper.map::userToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(ObjectId id) {
        User user = userDao.getUserById(id).orElseThrow(UserNotFoundException::new);
        return UserMapper.map.userToUserResponse(user);
    }

    public UserResponse updateUser(ObjectId id, User updatedUser) {
        userDao.updateUser(id, updatedUser);
        User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.map.userToUserResponse(user);
    }

    public void deleteUser(ObjectId id) {
        userDao.deleteUser(id);
    }
}