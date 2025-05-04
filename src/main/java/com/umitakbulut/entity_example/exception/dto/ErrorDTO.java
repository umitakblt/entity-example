package com.umitakbulut.entity_example.exception.dto;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ErrorDTO implements Serializable {
    private String message;
    private HttpStatus status;
    private int code;
    private String key;

    public ErrorDTO(String message, HttpStatus status, int code, String key) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.key = key;
    }

    public ErrorDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
