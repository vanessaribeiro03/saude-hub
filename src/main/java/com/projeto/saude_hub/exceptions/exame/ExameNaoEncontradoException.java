package com.projeto.saude_hub.exceptions.exame;

public class ExameNaoEncontradoException extends RuntimeException {
    public ExameNaoEncontradoException (String message){
        super(message);
    }
}
