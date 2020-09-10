package com.springboot.todo.ToDoList.dto.Response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BoardResponse {

    private String userID;

    private String name;

    private String id;

    private Date createDate;

    private Date updateDate;
}
