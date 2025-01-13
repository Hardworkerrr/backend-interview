package com.example.demo.exception;

import com.example.demo.exception.description.ExceptionExtension;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class RestGenericException extends RuntimeException {

  private final ExceptionExtension exceptionExtension;
  private final HttpStatus httpStatus;

  protected RestGenericException(ExceptionExtension exceptionExtension, HttpStatus httpStatus) {
    super(exceptionExtension.message());
    this.exceptionExtension = exceptionExtension;
    this.httpStatus = httpStatus;
  }
}
