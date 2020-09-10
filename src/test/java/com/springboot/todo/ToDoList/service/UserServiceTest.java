package com.springboot.todo.ToDoList.service;

import com.springboot.todo.ToDoList.dto.Response.UserResponse;
import com.springboot.todo.ToDoList.exception.UserNotFoundException;
import com.springboot.todo.ToDoList.model.User;
import com.springboot.todo.ToDoList.repository.UserRepository;
import com.springboot.todo.ToDoList.resource.UserResource;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RestClientTest(UserServiceTest.class) // annotation is used to test service layer
@RunWith(MockitoJUnitRunner.class) // to use mockito annotation
public class UserServiceTest {

    @Mock // we can use @Mock to create and inject mocked instances without having to call Mockito.mock manually.
    private UserRepository userRepository;

    @InjectMocks // to use userService getters and setters
    private UserService userService;

    private ObjectId userId;

    private User user;

    private UserResponse userResponse;

    @Before // is executed before each test
    public void init() {
        userId = (new ObjectId("5e9629b43962087948122ca0"));
        user = UserResource.user;
        doNothing().when(userRepository).updateUser(any(), any());
        when(userRepository.getUserById(any())).thenReturn(Optional.of(user));
    }


    @Test(expected = UserNotFoundException.class) // optional ‘expected’ attribute
    public void getUser_userNotFoundException() {
        when(userRepository.getUserById(any())).thenReturn(Optional.empty());
        userService.getUserById(userId);
    }

    @Test
    public void getUser_getUserWithProperties() {
        UserResponse userResponse = userService.getUserById(userId);
        Assert.assertNotNull(userResponse.getFirstName());
        Assert.assertEquals(user.getFirstName(), userResponse.getFirstName());
        Assert.assertNotNull(userResponse.getLastName());
        Assert.assertEquals(user.getLastName(), userResponse.getLastName());
    }
}
