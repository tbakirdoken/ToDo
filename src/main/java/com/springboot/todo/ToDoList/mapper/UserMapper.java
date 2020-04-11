package com.springboot.todo.ToDoList.mapper;

import com.springboot.todo.ToDoList.dto.Response.UserResponse;
import com.springboot.todo.ToDoList.mapper.customize.ObjectIdMapper;
import com.springboot.todo.ToDoList.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface UserMapper {

    UserMapper map = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    //User userRequestToUser(UserDetailRequest userDetailRequest);
}
