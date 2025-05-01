package com.umitakbulut.entity_example.service.impl;

import com.umitakbulut.entity_example.repository.UserRepository;
import com.umitakbulut.entity_example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
