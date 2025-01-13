package com.example.demo.config;

import static com.example.demo.util.Constants.PROCESSORS_CONFIGURATION_FAILED_MESSAGE;
import static com.example.demo.util.Constants.PROCESSOR_FOR_TRANSACTION_TYPE_NOT_FOUND_MESSAGE;

import com.example.demo.model.TransactionType;
import com.example.demo.service.strategy.TransactionStatusUpdateStrategy;
import java.util.EnumMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TransactionStatusUpdateConfig {

  @Bean
  public Map<TransactionType, TransactionStatusUpdateStrategy> transactionStatusUpdateProcessors(
      Map<String, TransactionStatusUpdateStrategy> processorsBeans) {
    Map<TransactionType, TransactionStatusUpdateStrategy> activityProcessorsMap =
        new EnumMap<>(TransactionType.class);
    for (TransactionType source : TransactionType.values()) {
      TransactionStatusUpdateStrategy processor = processorsBeans.get(source.name());
      if (processor != null) {
        activityProcessorsMap.put(source, processor);
      } else {
        log.error(PROCESSOR_FOR_TRANSACTION_TYPE_NOT_FOUND_MESSAGE, source.name());
        throw new RuntimeException(PROCESSORS_CONFIGURATION_FAILED_MESSAGE);
      }
    }
    return activityProcessorsMap;
  }
}
