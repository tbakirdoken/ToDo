package com.springboot.todo.ToDoList.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class BaseModel {

    @Id
    private ObjectId id;

    private Date createDate;

    private Date updateDate;

    BaseModel() {

        this.createDate = new Date();
        this.updateDate = new Date();
    }
}
