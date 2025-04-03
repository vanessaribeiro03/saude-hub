package com.projeto.saudehub.exceptions.usuario;

public class EmailExisteException extends RuntimeException{
    public EmailExisteException(String message){
        super(message);
    }
}
