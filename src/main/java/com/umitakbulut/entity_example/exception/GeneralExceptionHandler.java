package com.umitakbulut.entity_example.exception;

import com.umitakbulut.entity_example.exception.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> authorizationExceptionHandler(UserException userException){
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(userException.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), userException.getKey()),HttpStatus.BAD_REQUEST);
    }
}
