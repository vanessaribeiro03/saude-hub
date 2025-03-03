package com.projeto.saudehub.exceptions.medicamentos;

public class MedicamentoNaoEncontradoException extends RuntimeException{
    public MedicamentoNaoEncontradoException (String message){
        super(message);
    }
}
