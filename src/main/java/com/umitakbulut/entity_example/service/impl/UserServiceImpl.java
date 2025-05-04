package com.umitakbulut.entity_example.service.impl;

import com.umitakbulut.entity_example.dto.request.CreateUserRequest;
import com.umitakbulut.entity_example.dto.response.UserResponse;
import com.umitakbulut.entity_example.entity.User;
import com.umitakbulut.entity_example.exception.UserException;
import com.umitakbulut.entity_example.exception.UserExceptionEnum;
import com.umitakbulut.entity_example.repository.UserRepository;
import com.umitakbulut.entity_example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    private final static String REGEX = "\\d+";
    private final static Character ZERO = '0';
    private final static Character ONE = '1';
    private final static Character THREE = '3';
    private final static Character FIVE = '5';
    private final static Character SEVEN = '7';
    private final static Character NINE = '9';

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        log.info("UserServiceImpl.createUser(): createUserRequest {}", createUserRequest);

        if (!this.identifyNumberCheck(createUserRequest.getIdentifyNumber())) {
            log.error("UserServiceImpl.createUser(): identifyNumber is invalid");
            throw new UserException(UserExceptionEnum.IDENTIFY_NUMBER_IS_NOT_CORRECT);
        }

        User user = new User(createUserRequest.getName(),
                createUserRequest.getSurName(),
                createUserRequest.getEmail(),
                createUserRequest.getPhoneNumber(),
                createUserRequest.getIdentifyNumber());

        User savedUser = userRepository.save(user);
        log.info("UserServiceImpl.createUser(): savedUser {}", savedUser);

        return new UserResponse(savedUser.getName(),
                savedUser.getSurName(),
                savedUser.getEmail(),
                savedUser.getPhoneNumber());
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

    private boolean identifyNumberCheck (String identifyNumber) {
        if (identifyNumber.length() != 11 ||
            !identifyNumber.matches(REGEX) ||
            identifyNumber.charAt(0) != ZERO ||
            identifyNumber.charAt(10) == ONE ||
            identifyNumber.charAt(10) == THREE ||
            identifyNumber.charAt(10) == FIVE ||
            identifyNumber.charAt(10) == SEVEN ||
            identifyNumber.charAt(10) == NINE)
            return false;

        int single;
        int couple;

        single = Character.getNumericValue(identifyNumber.charAt(0))
                + Character.getNumericValue(identifyNumber.charAt(2))
                + Character.getNumericValue(identifyNumber.charAt(4))
                + Character.getNumericValue(identifyNumber.charAt(6))
                + Character.getNumericValue(identifyNumber.charAt(8));

        couple = Character.getNumericValue(identifyNumber.charAt(1))
                + Character.getNumericValue(identifyNumber.charAt(3))
                + Character.getNumericValue(identifyNumber.charAt(5))
                + Character.getNumericValue(identifyNumber.charAt(7));

        int result = (single * 7 - couple) % 10;

        return result == Character.getNumericValue(identifyNumber.charAt(9));
    }
}
