package com.umitakbulut.entity_example.service.impl;

import com.umitakbulut.entity_example.dto.request.CreateUserRequest;
import com.umitakbulut.entity_example.dto.response.UserResponse;
import com.umitakbulut.entity_example.entity.User;
import com.umitakbulut.entity_example.repository.UserRepository;
import com.umitakbulut.entity_example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        log.info("UserServiceImpl.createUser(): createUserRequest {}", createUserRequest);

        // TCKN Kontrol

        User user = new User();
        user.setName(createUserRequest.getName());
        user.setSurName(createUserRequest.getSurName());
        user.setEmail(createUserRequest.getEmail());
        user.setPhoneNumber(createUserRequest.getPhoneNumber());

        User savedUser = userRepository.save(user);
        log.info("UserServiceImpl.createUser(): savedUser {}", savedUser);

        UserResponse userResponse = new UserResponse();
        userResponse.setName(savedUser.getName());
        userResponse.setSurname(savedUser.getSurName());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPhoneNumber(savedUser.getPhoneNumber());

        return userResponse;
    }

    @Override
    public List<UserResponse> listUser() {
        log.info("UserServiceImpl.listUser(): listUser");

        List<User> userList = userRepository.findAll();
        log.info("UserServiceImpl.listUser(): userList {}", userList);

        return userList.stream().map(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getName());
            userResponse.setSurname(user.getSurName());
            userResponse.setEmail(user.getEmail());
            userResponse.setPhoneNumber(user.getPhoneNumber());
            return userResponse;
        }).toList();
    }
}
