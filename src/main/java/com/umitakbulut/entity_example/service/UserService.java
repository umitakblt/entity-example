package com.umitakbulut.entity_example.service;

import com.umitakbulut.entity_example.dto.request.CreateUserRequest;
import com.umitakbulut.entity_example.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest createUserRequest);

    List<UserResponse> listUser();
}
