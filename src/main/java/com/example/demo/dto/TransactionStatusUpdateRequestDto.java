package com.example.demo.dto;

import com.example.demo.model.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatusUpdateRequestDto {

  @JsonProperty(value = "newStatus")
  @NotNull
  private TransactionStatus newStatus;
}
