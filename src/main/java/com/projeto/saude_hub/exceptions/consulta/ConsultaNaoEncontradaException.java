package com.projeto.saude_hub.exceptions.consulta;

public class ConsultaNaoEncontradaException extends RuntimeException{
    public ConsultaNaoEncontradaException(String message){
        super(message);
    }
}
