package com.test.service;

import com.test.payload.UserLoginDto;
import com.test.payload.UserSignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public String saveUser(UserSignUpDto signupDtoUser);
    public String authenticateUser(UserLoginDto userLoginDto);
}
