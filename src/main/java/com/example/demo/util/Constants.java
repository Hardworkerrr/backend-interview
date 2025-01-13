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
  public static final String PROCESSOR_FOR_TRANSACTION_TYPE_NOT_FOUND_MESSAGE =
      "Update status processor for transaction type: {}, not found";
  public static final String DEPOSIT_TYPE_BEAN_NAME = "DEPOSIT";
  public static final String WITHDRAWAL_TYPE_BEAN_NAME = "WITHDRAWAL";
  public static final String PROCESSORS_CONFIGURATION_FAILED_MESSAGE =
      "Update status processors configuration failed";
  public static final String FAILED_TO_PUBLISH_TRANSACTION_EVENT_KAFKA_MESSAGE =
      "Failed to publish transaction event to Kafka";
  public static final String FAILED_TO_DESERIALIZE_EVENT_KAFKA_MESSAGE =
      "Failed to process message: {}";
}
