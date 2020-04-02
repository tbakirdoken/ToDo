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
@Document("Items")
public class Item {

    @Id
    private String ID;

    private String boardID;

    private String name;

    private String description;

    private boolean status;

    private Date deadline;

    private Date createdAt;

    private Date updatedAt;
}
