package com.springboot.todo.ToDoList.exception;

public class EmailExistException extends BaseException {
    public EmailExistException() {
        super(ExceptionCode.EMAIL_EXIST);
    }
}
