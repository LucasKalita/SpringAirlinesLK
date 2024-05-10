package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.exceptions.WrongUserIDException;
import com.lucaskalita.airlines.utilities.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {
    @ExceptionHandler(WrongUserIDException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ExceptionDTO userException(WrongUserIDException wrongUserIDException){
        return ExceptionDTO.builder()
                  .message("User not found")
                  .build();
    }
}
