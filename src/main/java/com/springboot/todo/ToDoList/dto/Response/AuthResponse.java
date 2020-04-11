package com.springboot.todo.ToDoList.dto.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String id;

    private String token;
}
