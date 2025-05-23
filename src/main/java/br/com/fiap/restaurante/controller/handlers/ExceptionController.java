package br.com.fiap.restaurante.controller.handlers;

import br.com.fiap.restaurante.exceptions.ErrorResponse;
import br.com.fiap.restaurante.exceptions.LoginExistsException;
import br.com.fiap.restaurante.exceptions.SenhaFracaException;
import br.com.fiap.restaurante.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ValidationException.class)
    public void handlePersonalValidation(){
        //validacao personalizada se houver
    }

    // exception de senha fraca
    @ExceptionHandler(SenhaFracaException.class)
    public ResponseEntity<ErrorResponse> handleSenhaFracaException(SenhaFracaException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(error);

    }


    // exception de login ja em uso
    @ExceptionHandler(LoginExistsException.class)
    public ResponseEntity<ErrorResponse> handleLoginExistException(LoginExistsException ex) {

        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(error);
    }

    //TODO - retornar uma lista de error response, padronizada
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>>
    handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        Map<String, List<String>> errorMap = new HashMap<>();

        errors.forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.computeIfAbsent(fieldName, k -> new java.util.ArrayList<>()).add(errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }






}
