package com.example.demo.service;

import com.example.demo.model.Balance;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.entity.BalanceEntity;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultBalanceService implements BalanceService {

  private final BalanceRepository balanceRepository;

  @Override
  public Balance getOrCreate(String currency) {
    return balanceRepository
        .findByCurrency(currency)
        .orElseGet(() -> balanceRepository.save(new BalanceEntity(currency)));
  }

  @Override
  @Transactional
  public void withdraw(Balance balance, BigDecimal amount) {
    BigDecimal newBalanceAmount = balance.getAmount().subtract(amount);
    BalanceEntity balanceEntity = mapBalanceToBalanceEntity(balance);
    balanceEntity.setAmount(newBalanceAmount);
  }

  @Override
  @Transactional
  public void deposit(Balance balance, BigDecimal amount) {
    BalanceEntity balanceEntity = mapBalanceToBalanceEntity(balance);
    balanceEntity.setAmount(balanceEntity.getAmount().add(amount));
  }

  // Maybe should create mapstruct mapper in the future, but as for now it's normal solution
  private BalanceEntity mapBalanceToBalanceEntity(Balance balance) {
    if (balance instanceof BalanceEntity balanceEntity) {
      return balanceEntity;
    } else {
      throw new ClassCastException("Cannot map Balance to BalanceEntity");
    }
  }
}
