package com.livmall.portaria.config;


import com.livmall.portaria.config.execoes.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationError(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> message = bindingResult.getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage()).
                collect(Collectors.toList());
        return new ApiError(message);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusExceotion(ResponseStatusException ex){
        String mensagemErro = ex.getMessage();
        HttpStatus codigoStatus =  ex.getStatus();
        ApiError apiError = new ApiError(mensagemErro);
        return  new ResponseEntity(apiError, codigoStatus);
    }

}
