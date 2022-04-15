package com.pleshkov.smartcontractgenerator.controller;

import com.pleshkov.smartcontractgenerator.model.exception.InvalidParameterException;
import com.pleshkov.smartcontractgenerator.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    public ResponseEntity<ErrorResponse> handleWrongArgumentException(InvalidParameterException e){
        log.error("Error occurred: ", e);
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
