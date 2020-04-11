package com.springboot.todo.ToDoList.mapper;

import com.springboot.todo.ToDoList.dto.Request.AuthRequest;
import com.springboot.todo.ToDoList.dto.Response.AuthResponse;
import com.springboot.todo.ToDoList.mapper.customize.ObjectIdMapper;
import com.springboot.todo.ToDoList.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface AuthMapper {


    AuthMapper map = Mappers.getMapper(AuthMapper.class);

    User authRequestToUser(AuthRequest authRequest);

    AuthResponse userToAuthResponse(User user);

}