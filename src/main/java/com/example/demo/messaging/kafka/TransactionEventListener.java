package com.example.demo.messaging.kafka;

import static com.example.demo.util.Constants.FAILED_TO_PUBLISH_TRANSACTION_EVENT_KAFKA_MESSAGE;

import com.example.demo.messaging.kafka.event.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionEventListener {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value("${spring.kafka.consumer.topic}")
  private String transactionTopic;

  // I could implement strategy pattern with Events handling also, but no time
  @EventListener
  public void handleTransactionEvent(TransactionEvent event) {
    try {
      kafkaTemplate.send(transactionTopic, event.getTransaction());
    } catch (Exception e) {
      log.error(FAILED_TO_PUBLISH_TRANSACTION_EVENT_KAFKA_MESSAGE, e);
    }
  }
}
