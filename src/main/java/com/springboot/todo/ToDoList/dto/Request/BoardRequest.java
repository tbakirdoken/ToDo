package com.springboot.todo.ToDoList.dto.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRequest {

    String name;
}
