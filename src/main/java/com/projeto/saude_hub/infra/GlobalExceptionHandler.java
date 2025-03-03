package com.projeto.saude_hub.infra;

import com.projeto.saude_hub.exceptions.CamposNulosException;
import com.projeto.saude_hub.exceptions.consulta.ConsultaNaoEncontradaException;
import com.projeto.saude_hub.exceptions.exame.ExameNaoEncontradoException;
import com.projeto.saude_hub.exceptions.medicamentos.MedicamentoNaoEncontradoException;
import com.projeto.saude_hub.exceptions.usuario.UsuarioNaoEncontradoException;
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
            ConsultaNaoEncontradaException.class
    })
    public ResponseEntity<RestErrorMessage> handleNotFoundException(Exception ex){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
