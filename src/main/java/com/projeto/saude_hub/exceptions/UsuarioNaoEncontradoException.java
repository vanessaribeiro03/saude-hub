package com.projeto.saude_hub.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(Long id){
        super("Usuário não encontrado.");
    }

    public UsuarioNaoEncontradoException(String message){
        super(message);
    }
}
