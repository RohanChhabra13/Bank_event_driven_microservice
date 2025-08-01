package com.bank.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String resourceName, String filedName, String fieldValue){
        super (String.format("%snot found with the given input data %s: '%s'",resourceName,filedName,fieldValue));
    }
}
