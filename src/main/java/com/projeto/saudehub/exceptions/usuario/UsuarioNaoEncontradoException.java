package com.projeto.saudehub.exceptions.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String message){
        super(message);
    }
}
