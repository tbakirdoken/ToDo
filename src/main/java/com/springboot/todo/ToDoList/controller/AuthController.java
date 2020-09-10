package com.springboot.todo.ToDoList.controller;

import com.springboot.todo.ToDoList.dto.Request.AuthRequest;
import com.springboot.todo.ToDoList.dto.Response.AuthResponse;
import com.springboot.todo.ToDoList.service.AuthService;
import com.springboot.todo.ToDoList.util.ResponseUtil;
import com.springboot.todo.ToDoList.viewmodel.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    @NotNull
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<MainResponse<AuthResponse>> register(@Valid AuthRequest authRequest) {

        return ResponseUtil.data(authService.registerUser(authRequest), HttpStatus.CREATED);

    }

    @PostMapping("login")
    public ResponseEntity<MainResponse<AuthResponse>> login(
            @RequestBody AuthRequest authRequest) {
        return ResponseUtil.data(authService.loginUser(authRequest.getEmail(), authRequest.getPassword()));
    }
}
