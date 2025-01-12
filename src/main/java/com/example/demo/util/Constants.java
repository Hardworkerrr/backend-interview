package com.example.demo.util;

public class Constants {

  private Constants() {}

  public static final String TRANSACTION_MIN_AMOUNT = "0.01";
  public static final String TRANSACTION_MIN_AMOUNT_ERROR_MESSAGE =
      "Invalid transaction amount. Must be greater than 0.01";
  public static final String REFERENCE_DUPLICATION_EXCEPTION_MESSAGE =
      "Transaction with the reference: %s, already exists";
  public static final String TRANSACTION_STATUS_UPDATE_EXCEPTION_MESSAGE =
      "Transaction status update is not allowed for transaction with id: %s";
  public static final String ERROR_CODE = "errorCode";
  public static final String MESSAGE = "message";
  public static final String DESCRIPTION = "description";
}
