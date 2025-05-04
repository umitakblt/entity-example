package com.umitakbulut.entity_example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserException extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(UserException.class);
    private String key;

    public UserException(UserExceptionEnum userExceptionEnum) {
        super(userExceptionEnum.getMessage());
        this.key = userExceptionEnum.getKey();
    }

    public String getKey() {
        return key;
    }
}
