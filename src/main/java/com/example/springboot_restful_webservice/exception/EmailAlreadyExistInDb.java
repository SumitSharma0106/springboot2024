package com.example.springboot_restful_webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistInDb extends RuntimeException{

    private String message;

    public EmailAlreadyExistInDb(String message){
        super(message);
    }

}
