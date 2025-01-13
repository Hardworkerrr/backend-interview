package com.example.demo.service.strategy.impl;

import static com.example.demo.model.TransactionStatus.ERROR;
import static com.example.demo.util.Constants.WITHDRAWAL_TYPE_BEAN_NAME;

import com.example.demo.model.Balance;
import com.example.demo.model.TransactionStatus;
import com.example.demo.repository.entity.TransactionEntity;
import com.example.demo.service.BalanceService;
import com.example.demo.service.strategy.TransactionStatusUpdateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component(WITHDRAWAL_TYPE_BEAN_NAME)
@RequiredArgsConstructor
public class WithdrawalTransactionTypeUpdateStatus implements TransactionStatusUpdateStrategy {

  private final BalanceService balanceService;

  @Override
  public void updateStatus(TransactionEntity transaction, TransactionStatus newStatus) {
    switch (newStatus) {
      case ERROR -> {
        Balance balance = balanceService.getOrCreate(transaction.getCurrency());
        balanceService.deposit(balance, transaction.getAmount());
        transaction.setStatus(ERROR);
      }
      default -> transaction.setStatus(newStatus);
    }
  }
}
