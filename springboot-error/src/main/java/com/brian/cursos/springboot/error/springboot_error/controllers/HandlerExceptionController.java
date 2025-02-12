package com.brian.cursos.springboot.error.springboot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.brian.cursos.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.brian.cursos.springboot.error.springboot_error.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<Error> divisionByZero(Exception ex){

        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error division por cero!");
        error.setMesaage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.internalServerError().body(error);
        
       // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }


    @ExceptionHandler(NumberFormatException.class)
    //Alternativa
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> NumberFormatException(Exception ex){
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Numero invalido o incorrecto, no tiene formato el digito!");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return error;
    }

    @ExceptionHandler({NullPointerException.class,HttpMessageNotWritableException.class, UserNotFoundException.class})
    //Alternativa
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(Exception ex){
        
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "El usuarios o role no existe!");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return error;
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException e){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api rest no encontrado");
        error.setMesaage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
       
       // return ResponseEntity.status(404).body(error);
        //Alternativas
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
       // return ResponseEntity.notFound().build();
    }
}
