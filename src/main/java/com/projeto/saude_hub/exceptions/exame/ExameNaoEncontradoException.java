package com.projeto.saude_hub.exceptions.exame;

public class ExameNaoEncontradoException extends RuntimeException {
    public ExameNaoEncontradoException (Long id){
        super("Exame não encontrado");
    }

    public ExameNaoEncontradoException (String message){
        super(message);
    }
}
