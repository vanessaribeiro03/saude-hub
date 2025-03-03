package com.projeto.saude_hub.infra.usuario;

import com.projeto.saude_hub.exceptions.usuario.CamposNulosUsuarioException;
import com.projeto.saude_hub.exceptions.usuario.EmailExisteException;
import com.projeto.saude_hub.exceptions.usuario.UsuarioNaoEncontradoException;
import com.projeto.saude_hub.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UsuarioExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CamposNulosUsuarioException.class)
    private ResponseEntity<RestErrorMessage> camposNulos(CamposNulosUsuarioException exception){
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
