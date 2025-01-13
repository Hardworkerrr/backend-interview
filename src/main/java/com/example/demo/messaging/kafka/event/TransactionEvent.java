package com.example.demo.messaging.kafka.event;

import com.example.demo.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class TransactionEvent {
  public enum Type {
    CREATED,
    UPDATED
  }

  Transaction transaction;
  Type type;
}
