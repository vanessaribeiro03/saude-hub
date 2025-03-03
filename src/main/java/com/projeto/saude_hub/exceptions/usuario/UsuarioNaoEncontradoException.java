package com.projeto.saude_hub.exceptions.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String message){
        super(message);
    }
}
