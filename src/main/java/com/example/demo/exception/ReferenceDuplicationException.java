package com.example.demo.exception;

import static com.example.demo.exception.ErrorCode.ERR002;
import static org.springframework.http.HttpStatus.CONFLICT;

public class ReferenceDuplicationException extends RestGenericException {

  public ReferenceDuplicationException(String message) {
    super(ExceptionExtension.builder().message(message).errorCode(ERR002).build(), CONFLICT);
  }
}
