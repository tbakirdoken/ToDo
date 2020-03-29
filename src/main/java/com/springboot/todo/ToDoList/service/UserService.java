package com.springboot.todo.ToDoList.service;

import com.mongodb.client.result.UpdateResult;
import com.springboot.todo.ToDoList.dao.UserDao;
import com.springboot.todo.ToDoList.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("Users") UserDao userDao) {
        this.userDao = userDao;
    }

    public User addUser(User user) {

        return userDao.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public Optional<User> getUserById(String id){
        return userDao.getUserById(id);
    }

    public User updateUser(String id, User updatedUser) {
        userDao.updateUser(id, updatedUser);
        Optional<User> userOp = userDao.getUserById(id);
        //User user = userOp.(String::new);
        User user = userOp.get();
        return user;
    }

    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }
}
