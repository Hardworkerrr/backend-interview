package com.example.demo.service.strategy.impl;

import static com.example.demo.model.TransactionStatus.SUCCESS;
import static com.example.demo.util.Constants.DEPOSIT_TYPE_BEAN_NAME;

import com.example.demo.model.Balance;
import com.example.demo.model.TransactionStatus;
import com.example.demo.repository.entity.TransactionEntity;
import com.example.demo.service.BalanceService;
import com.example.demo.service.strategy.TransactionStatusUpdateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component(DEPOSIT_TYPE_BEAN_NAME)
@RequiredArgsConstructor
public class DepositTransactionTypeUpdateStatus implements TransactionStatusUpdateStrategy {

  private final BalanceService balanceService;

  @Override
  public void updateStatus(TransactionEntity transaction, TransactionStatus newStatus) {
    switch (newStatus) {
      case SUCCESS -> {
        Balance balance = balanceService.getOrCreate(transaction.getCurrency());
        balanceService.deposit(balance, transaction.getAmount());
        transaction.setStatus(SUCCESS);
      }
      default -> transaction.setStatus(newStatus);
    }
  }
}
