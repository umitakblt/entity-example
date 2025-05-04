package com.umitakbulut.entity_example.exception;

public enum UserExceptionEnum {
    IDENTIFY_NUMBER_IS_NOT_CORRECT("Identify number is not correct", "IDENTIFY_NUMBER_IS_NOT_CORRECT");

    private final String message;
    private final String key;

    UserExceptionEnum(String message, String key) {
        this.message = message;
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public String getKey() {
        return key;
    }
}
