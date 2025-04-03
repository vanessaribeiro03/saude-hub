package com.projeto.saudehub.infra;

import com.projeto.saudehub.exceptions.CamposNulosException;
import com.projeto.saudehub.exceptions.consulta.ConsultaNaoEncontradaException;
import com.projeto.saudehub.exceptions.exame.ExameNaoEncontradoException;
import com.projeto.saudehub.exceptions.medicamentos.MedicamentoNaoEncontradoException;
import com.projeto.saudehub.exceptions.usuario.EmailExisteException;
import com.projeto.saudehub.exceptions.usuario.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CamposNulosException.class)
    public ResponseEntity<RestErrorMessage> handleCamposNulosException(CamposNulosException ex){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler({
            UsuarioNaoEncontradoException.class,
            ExameNaoEncontradoException.class,
            MedicamentoNaoEncontradoException.class,
            ConsultaNaoEncontradaException.class,
            EmailExisteException.class
    })
    public ResponseEntity<RestErrorMessage> handleNotFoundException(Exception ex){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
