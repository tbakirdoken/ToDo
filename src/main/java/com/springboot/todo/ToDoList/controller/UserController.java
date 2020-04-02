package com.springboot.todo.ToDoList.controller;

import com.google.common.collect.Lists;
import com.springboot.todo.ToDoList.model.User;
import com.springboot.todo.ToDoList.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@NotNull @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "10", required = false) int pageSize, @RequestParam(defaultValue = "ID", required = false) String sortBy) {
        return userService.getAllUsers(page, pageSize, sortBy);
    }

    @GetMapping(path = "{id}")
    Optional<User> getUserById(@NotNull @PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @PutMapping(path = "{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }




}
