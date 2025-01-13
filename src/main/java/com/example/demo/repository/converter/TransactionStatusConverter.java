package com.example.demo.repository.converter;

import com.example.demo.model.TransactionStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {

  @Override
  public String convertToDatabaseColumn(final TransactionStatus value) {
    return value != null ? value.name().toLowerCase() : null;
  }

  @Override
  public TransactionStatus convertToEntityAttribute(final String value) {
    return value != null ? TransactionStatus.valueOf(value.toUpperCase()) : null;
  }
}
