package com.example.demo.service;

import static com.example.demo.util.Constants.REFERENCE_DUPLICATION_EXCEPTION_MESSAGE;
import static com.example.demo.util.Constants.TRANSACTION_STATUS_UPDATE_EXCEPTION_MESSAGE;

import com.example.demo.exception.ReferenceDuplicationException;
import com.example.demo.exception.TransactionStatusUpdateException;
import com.example.demo.model.Balance;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionStatus;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.entity.TransactionEntity;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultTransactionService implements TransactionService {

  private final BalanceService balanceService;
  private final TransactionRepository transactionRepository;

  @Override
  @Transactional
  public Transaction create(
      final TransactionType type,
      final String reference,
      final BigDecimal amount,
      final String currency) {
    Balance balance = balanceService.getOrCreate(currency);

    try {
      return transactionRepository.save(
          new TransactionEntity(balance.getId(), reference, type, amount, currency));
    } catch (DataIntegrityViolationException e) {
      throw new ReferenceDuplicationException(
          REFERENCE_DUPLICATION_EXCEPTION_MESSAGE.formatted(reference));
    }
  }

  @Override
  @Transactional
  public void updateStatus(long id, TransactionStatus newStatus) {
    Transaction transaction = get(id);
    if (transaction instanceof TransactionEntity transactionEntity) {
      if (transactionStatusIsUpdatable(transactionEntity, newStatus)) {
        transactionEntity.setStatus(newStatus);
      } else {
        throw new TransactionStatusUpdateException(
            TRANSACTION_STATUS_UPDATE_EXCEPTION_MESSAGE.formatted(id));
      }
    }
  }

  @Override
  public Transaction toSuccess(long id) {
    // TODO implement
    return null;
  }

  @Override
  public Transaction toError(long id) {
    // TODO implement
    return null;
  }

  @Override
  public Optional<Transaction> find(long id) {
    return transactionRepository.findById(id).map(x -> x);
  }

  private boolean transactionStatusIsUpdatable(
      TransactionEntity transaction, TransactionStatus newStatus) {
    return !transaction.getStatus().equals(newStatus) && !transaction.getStatus().isFinal();
  }
}
