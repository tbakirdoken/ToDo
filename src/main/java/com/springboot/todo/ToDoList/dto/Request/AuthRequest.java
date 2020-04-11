package com.springboot.todo.ToDoList.dto.Request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {


    @NotBlank(message = "{email.not.blank}")
    private String email;

    @NotBlank(message = "{first.name.not.blank}")
    private String firstName;

    @NotBlank(message = "{last.name.not.blank}")
    private String lastName;

    @NotBlank(message = "{password.not.blank}")
    private String password;

}