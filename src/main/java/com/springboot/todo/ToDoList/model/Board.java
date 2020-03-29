package com.springboot.todo.ToDoList.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Boards")
public class Board {

    @Id
    private String ID;

    private String userID;

    private String name;

    private Date createdAt;

    private Date updatedAt;
}
