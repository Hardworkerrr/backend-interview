package com.example.demo.messaging.kafka;

import static com.example.demo.util.Constants.FAILED_TO_DESERIALIZE_EVENT_KAFKA_MESSAGE;

import com.example.demo.repository.TransactionDocumentRepository;
import com.example.demo.repository.document.TransactionDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionTopicConsumer {

  private final TransactionDocumentRepository transactionDocumentRepository;
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = "${spring.kafka.consumer.topic}",
      groupId = "${spring.kafka.consumer.group-id}")
  public void consumeTransactionMessage(String message) {
    try {
      TransactionDocument transactionDocument =
          objectMapper.readValue(message, TransactionDocument.class);
      transactionDocumentRepository.save(transactionDocument);
    } catch (Exception e) {
      log.error(FAILED_TO_DESERIALIZE_EVENT_KAFKA_MESSAGE, message, e);
    }
  }
}
