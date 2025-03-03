package com.projeto.saude_hub.exceptions.medicamentos;

public class MedicamentoNaoEncontradoException extends RuntimeException{
    public MedicamentoNaoEncontradoException (String message){
        super(message);
    }
}
