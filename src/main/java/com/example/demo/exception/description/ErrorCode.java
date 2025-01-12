package com.example.demo.exception.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum ErrorCode {
  ERR001("ERR001", "Parameters validation failed"),
  ERR002("ERR002", "Unique constraint violated"),
  ERR003("ERR003", "Unprocessable operation");

  @JsonProperty("code")
  private final String code;

  @JsonProperty("description")
  private final String description;

  ErrorCode(String code, String description) {
    this.code = code;
    this.description = description;
  }
}
