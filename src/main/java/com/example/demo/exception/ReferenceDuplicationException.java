package com.example.demo.exception;

import static com.example.demo.exception.description.ErrorCode.ERR002;
import static org.springframework.http.HttpStatus.CONFLICT;

import com.example.demo.exception.description.ExceptionExtension;

public class ReferenceDuplicationException extends RestGenericException {

  public ReferenceDuplicationException(String message) {
    super(ExceptionExtension.builder().message(message).errorCode(ERR002).build(), CONFLICT);
  }
}
