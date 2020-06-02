package br.com.senac.usersystemservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EmailAlreadyExists extends  RuntimeException{
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
