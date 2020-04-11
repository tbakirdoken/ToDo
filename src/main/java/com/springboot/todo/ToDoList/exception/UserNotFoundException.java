package com.springboot.todo.ToDoList.exception;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(ExceptionCode.USER_NOT_FOUND);
    }
}
