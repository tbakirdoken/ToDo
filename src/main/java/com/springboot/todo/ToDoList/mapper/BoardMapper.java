package com.springboot.todo.ToDoList.mapper;

import com.springboot.todo.ToDoList.dto.Response.BoardResponse;
import com.springboot.todo.ToDoList.mapper.customize.ObjectIdMapper;
import com.springboot.todo.ToDoList.model.Board;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface BoardMapper {

    BoardMapper map = Mappers.getMapper(BoardMapper.class);

    BoardResponse boardToBoardResponse(Board board);

    //Board boardRequestToBoard(BoardRequest boardRequest);
}
