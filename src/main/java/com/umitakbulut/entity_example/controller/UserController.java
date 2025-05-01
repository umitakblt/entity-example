package com.umitakbulut.entity_example.controller;

import com.umitakbulut.entity_example.dto.request.CreateUserRequest;
import com.umitakbulut.entity_example.dto.response.UserResponse;
import com.umitakbulut.entity_example.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        log.info("UserController.createUser(): createUserRequest {}", createUserRequest);
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @GetMapping(value = "/list-user")
    public ResponseEntity<List<UserResponse>> listUser() {
        log.info("UserController.listUser(): listUser");
        return ResponseEntity.ok(userService.listUser());
    }
}
