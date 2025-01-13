package com.example.demo.service;

import com.example.demo.model.Balance;
import java.math.BigDecimal;

public interface BalanceService {

  Balance getOrCreate(String currency);

  void withdraw(Balance balance, BigDecimal amount);

  void deposit(Balance balance, BigDecimal amount);
}
