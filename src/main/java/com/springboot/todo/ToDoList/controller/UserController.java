package com.springboot.todo.ToDoList.controller;

import com.google.common.collect.Lists;
import com.springboot.todo.ToDoList.dto.Response.UserResponse;
import com.springboot.todo.ToDoList.model.User;
import com.springboot.todo.ToDoList.service.UserService;
import com.springboot.todo.ToDoList.util.ResponseUtil;
import com.springboot.todo.ToDoList.viewmodel.MainResponse;
import io.swagger.annotations.Api;
import org.bson.types.ObjectId;
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
@RequestMapping("/users")
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<MainResponse<UserResponse>> addUser(@NotNull @RequestBody User user) {
        return ResponseUtil.data(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MainResponse<List<UserResponse>>> getAllUsers(@RequestParam(defaultValue = "0", required = false) int page,
                                  @RequestParam(defaultValue = "10", required = false) int pageSize,
                                  @RequestParam(defaultValue = "id", required = false) String sortBy) {
        return ResponseUtil.data(userService.getAllUsers(page, pageSize, sortBy));
    }

    @GetMapping(path = "{id}")
    ResponseEntity<MainResponse<UserResponse>> getUserById(@NotNull @PathVariable("id") ObjectId id){
        return ResponseUtil.data(userService.getUserById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<MainResponse<UserResponse>> updateUser(@PathVariable("id") ObjectId id, @RequestBody User updatedUser) {
        return ResponseUtil.data(userService.updateUser(id, updatedUser));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<MainResponse<String>> deleteUser(@PathVariable("id") ObjectId id) {
        userService.deleteUser(id);
        return ResponseUtil.data("Deleted", HttpStatus.NO_CONTENT);
    }

}
