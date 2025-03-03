package com.projeto.saude_hub.exceptions.medicamentos;

public class MedicamentoNaoEncontradoException extends RuntimeException{
    public MedicamentoNaoEncontradoException (Long id){
        super("Medicamento não encontrado.");
    }

    public MedicamentoNaoEncontradoException (String message){
        super(message);
    }
}
