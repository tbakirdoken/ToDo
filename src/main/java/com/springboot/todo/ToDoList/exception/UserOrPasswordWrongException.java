package com.springboot.todo.ToDoList.exception;

public class UserOrPasswordWrongException extends BaseException {

    public UserOrPasswordWrongException() { super(ExceptionCode.USER_OR_PASSWORD_WRONG); }
}
