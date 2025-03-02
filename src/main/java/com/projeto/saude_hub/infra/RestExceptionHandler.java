package com.projeto.saude_hub.infra;

import com.projeto.saude_hub.exceptions.CamposNulosException;
import com.projeto.saude_hub.exceptions.EmailExisteException;
import com.projeto.saude_hub.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CamposNulosException.class)
    private ResponseEntity<RestErrorMessage> camposNulos(CamposNulosException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(EmailExisteException.class)
    private ResponseEntity<RestErrorMessage> emailExiste(EmailExisteException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    private ResponseEntity<RestErrorMessage> usuarioNaoEncontrado(UsuarioNaoEncontradoException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
