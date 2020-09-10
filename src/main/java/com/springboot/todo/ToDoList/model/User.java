package com.springboot.todo.ToDoList.model;

import lombok.*;
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
@Builder
public class User extends BaseModel{

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isActive = true;

    private Date lastLogin;

}
