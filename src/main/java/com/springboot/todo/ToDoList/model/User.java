package com.springboot.todo.ToDoList.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Users")
public class User {

    @Id
    private String ID;

    private String firstName;

    private String lastName;

    @NotBlank
    @NotNull
    private String email;

    @NotNull
    @Min(4)
    @Max(6)
    private String password;

    private boolean isActive;

    private Date lastLogin;

    private Date dateJoined;

}
