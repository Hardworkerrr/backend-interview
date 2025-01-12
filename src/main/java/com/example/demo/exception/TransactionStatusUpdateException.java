package com.example.demo.exception;

import static com.example.demo.exception.description.ErrorCode.ERR003;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.example.demo.exception.description.ExceptionExtension;

public class TransactionStatusUpdateException extends RestGenericException {

  public TransactionStatusUpdateException(String message) {
    super(
        ExceptionExtension.builder().message(message).errorCode(ERR003).build(),
        UNPROCESSABLE_ENTITY);
  }
}
