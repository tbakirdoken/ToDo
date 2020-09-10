package com.springboot.todo.ToDoList.resource;

import com.springboot.todo.ToDoList.model.User;

public class UserResource {
    public static User userRequest = User.builder()
            .firstName("Tubi")
            .lastName("chickyface")
            .email("tubi@tubi.com")
            .build();

    public static User user = User.builder()
            .firstName("test")
            .lastName("test")
            .build();
}
