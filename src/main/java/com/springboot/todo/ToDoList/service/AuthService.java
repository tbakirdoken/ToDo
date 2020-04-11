package com.springboot.todo.ToDoList.service;


import com.springboot.todo.ToDoList.dto.Request.AuthRequest;
import com.springboot.todo.ToDoList.dto.Response.AuthResponse;
import com.springboot.todo.ToDoList.exception.EmailExistException;
import com.springboot.todo.ToDoList.exception.UserNotFoundException;
import com.springboot.todo.ToDoList.exception.UserOrPasswordWrongException;
import com.springboot.todo.ToDoList.mapper.AuthMapper;
import com.springboot.todo.ToDoList.model.User;
import com.springboot.todo.ToDoList.repository.UserRepository;
import com.springboot.todo.ToDoList.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    @NotNull
    private final UserRepository userRepository;

    @NotNull
    private final JwtUtil jwtUtil;

    @NotNull
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthResponse registerUser(AuthRequest authRequest)  {
        User user = AuthMapper.map.authRequestToUser(authRequest);
        Optional<User> byEmail = userRepository.findByEmail(authRequest.getEmail());

        if (byEmail.isPresent()) {
            throw new EmailExistException();
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.addUser(user);

        AuthResponse authResponse = AuthMapper.map.userToAuthResponse(savedUser);
        authResponse.setToken(jwtUtil.createToken(savedUser.getId()));
        return authResponse;
    }


    public AuthResponse loginUser(String email, String password) {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(UserOrPasswordWrongException::new);
        if(!user.isActive()){
            throw new UserNotFoundException();
        }
        if ( bCryptPasswordEncoder.matches(password, user.getPassword())) {

            ObjectId id = user.getId();
            user.setLastLogin(new Date());
            userRepository.updateUser(id, user);

            AuthResponse authResponse = AuthMapper.map.userToAuthResponse(user);
            authResponse.setToken(jwtUtil.createToken(user.getId()));

            return authResponse;
        } else {
            throw new UserOrPasswordWrongException();
        }


    }
}
