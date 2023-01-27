package com.germant.springPractice.handler;

import com.germant.springPractice.dto.ErrorResponse;
import com.germant.springPractice.exception.NoSuchPersonExistException;
import com.germant.springPractice.exception.PersonAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(HttpStatus.CONFLICT)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchPersonExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse noSuchPersonExistExceptionHandler(
            NoSuchPersonExistException ex
    ) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = PersonAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse personAlreadyExistExceptionHandler(
            PersonAlreadyExistException ex
    ) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
