package com.example.demo.model;

import java.util.EnumSet;

public enum TransactionStatus {
  NEW,
  PROCESSING,
  SUCCESS,
  ERROR;

  private static final EnumSet<TransactionStatus> FINAL_STATUSES = EnumSet.of(SUCCESS, ERROR);

  public boolean isFinal() {
    return FINAL_STATUSES.contains(this);
  }
}
