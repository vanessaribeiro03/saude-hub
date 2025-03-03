package com.projeto.saude_hub.infra.medicamento;

import com.projeto.saude_hub.exceptions.exame.CamposNulosExameException;
import com.projeto.saude_hub.exceptions.exame.ExameNaoEncontradoException;
import com.projeto.saude_hub.exceptions.medicamentos.CamposNulosMedicamentoException;
import com.projeto.saude_hub.exceptions.medicamentos.MedicamentoNaoEncontradoException;
import com.projeto.saude_hub.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MedicamentoExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MedicamentoNaoEncontradoException.class)

    private ResponseEntity<RestErrorMessage> medicamentoNaoEncontrado(MedicamentoNaoEncontradoException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(CamposNulosMedicamentoException.class)
    private ResponseEntity<RestErrorMessage> camposNulos(CamposNulosMedicamentoException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
