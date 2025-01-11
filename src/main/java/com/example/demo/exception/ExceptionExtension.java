package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionExtension(String message, ErrorCode errorCode) implements Serializable {}
