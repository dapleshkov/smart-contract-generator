package com.pleshkov.smartcontractgenerator.controller;

import com.pleshkov.smartcontractgenerator.model.exception.ExceptionResponse;
import com.pleshkov.smartcontractgenerator.model.exception.InvalidParameterException;
import com.pleshkov.smartcontractgenerator.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Component
@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e){
        ExceptionResponse response = new ExceptionResponse();
        response.setError("Null pointer exception");
        response.setStatus(500);
        log.error("Error occurred: ", e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ErrorResponse> handleWrongArgumentException(InvalidParameterException e){
        log.error("Error occurred: ", e);
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
