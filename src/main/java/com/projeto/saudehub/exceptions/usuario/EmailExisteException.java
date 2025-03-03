package com.projeto.saudehub.exceptions.usuario;

public class EmailExisteException extends RuntimeException{
    public EmailExisteException(){
        super("O email informado já está em uso.");
    }

    public EmailExisteException(String message){
        super(message);
    }
}
