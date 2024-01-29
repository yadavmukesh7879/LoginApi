package com.test.serviceImpl;

import com.test.entity.Role;
import com.test.entity.User;
import com.test.payload.UserLoginDto;
import com.test.payload.UserSignUpDto;
import com.test.repository.RoleRepository;
import com.test.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserServiceImpl implements com.test.service.UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String saveUser(UserSignUpDto userSignUpDto) {
        if (userRepository.existsByUserName(userSignUpDto.getUserName())) {
            return "UserName is already registered !..";
        }
        if (userRepository.existsByEmail(userSignUpDto.getEmail())) {
            return "Email is already registered !..";
        }

        User user = new User();
        user.setUserName(userSignUpDto.getUserName());
        user.setName(userSignUpDto.getName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return "User register successfully....!!";
    }

    @Override
    public String authenticateUser(UserLoginDto userLoginDto) {
        User user = userRepository.findByUserNameOrEmail(userLoginDto.getUserNameOrEmail(), userLoginDto.getUserNameOrEmail()).get();
        String status = user.getStatus();
        if (!status.equalsIgnoreCase("InActive")) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUserNameOrEmail(), userLoginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "User Login Successfully....!!";
        } else {
            return "Please wait for admin approval...!!";
        }

    }
}
