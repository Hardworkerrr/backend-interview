package com.example.demo.dto;

import static com.example.demo.util.Constants.TRANSACTION_MIN_AMOUNT;
import static com.example.demo.util.Constants.TRANSACTION_MIN_AMOUNT_ERROR_MESSAGE;

import com.example.demo.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequestDTO {

  @JsonProperty(value = "type")
  @NotNull
  private TransactionType type;

  @JsonProperty(value = "amount")
  @NotNull
  @DecimalMin(value = TRANSACTION_MIN_AMOUNT, message = TRANSACTION_MIN_AMOUNT_ERROR_MESSAGE)
  private BigDecimal amount;

  @JsonProperty(value = "currency")
  @NotBlank
  private String currency;

  @JsonProperty(value = "reference")
  @NotBlank
  private String reference;
}
