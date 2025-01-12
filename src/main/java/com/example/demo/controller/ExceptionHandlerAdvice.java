package com.example.demo.controller;

import static com.example.demo.exception.description.ErrorCode.ERR001;
import static com.example.demo.util.Constants.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.example.demo.exception.description.ExceptionExtension;
import com.example.demo.exception.RestGenericException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errorsMap = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errorsMap.put(error.getField(), error.getDefaultMessage());
    }
    errorsMap.put(ERROR_CODE, ERR001.getCode());
    errorsMap.put(DESCRIPTION, ERR001.getDescription());
    return new ResponseEntity<>(errorsMap, BAD_REQUEST);
  }

  @ExceptionHandler(RestGenericException.class)
  public ResponseEntity<Object> handleReferenceDuplicationExceptions(RestGenericException ex) {
    ExceptionExtension exceptionExtension = ex.getExceptionExtension();
    return new ResponseEntity<>(
        Map.of(
            ERROR_CODE,
            exceptionExtension.errorCode().getCode(),
            DESCRIPTION,
            exceptionExtension.errorCode().getDescription(),
            MESSAGE,
            ex.getMessage()),
        ex.getHttpStatus());
  }
}
