package com.example.demo.service.strategy;

import com.example.demo.model.TransactionStatus;
import com.example.demo.repository.entity.TransactionEntity;

public interface TransactionStatusUpdateStrategy {

  void updateStatus(TransactionEntity transaction, TransactionStatus newStatus);
}
