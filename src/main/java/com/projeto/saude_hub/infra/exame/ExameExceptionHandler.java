package com.projeto.saude_hub.infra.exame;

import com.projeto.saude_hub.exceptions.consulta.CamposNulosConsultaException;
import com.projeto.saude_hub.exceptions.consulta.ConsultaNaoEncontradaException;
import com.projeto.saude_hub.exceptions.exame.CamposNulosExameException;
import com.projeto.saude_hub.exceptions.exame.ExameNaoEncontradoException;
import com.projeto.saude_hub.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class  ExameExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExameNaoEncontradoException.class)

    private ResponseEntity<RestErrorMessage> exameNaoEncontrado(ExameNaoEncontradoException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(CamposNulosExameException.class)
    private ResponseEntity<RestErrorMessage> camposNulos(CamposNulosExameException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}

