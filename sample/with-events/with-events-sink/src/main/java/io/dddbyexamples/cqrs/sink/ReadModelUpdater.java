package io.dddbyexamples.cqrs.sink;

import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dddbyexamples.cqrs.controller.Withdrawal;
import io.dddbyexamples.cqrs.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
class ReadModelUpdater {

	private final WithdrawalRepository withdrawalRepository;

	@KafkaListener(topics="example-kafka-test")
	public void handle(String kafkaMessage, Acknowledgment acknowledgment) {
		
		ObjectMapper mapper = new ObjectMapper();
		CardWithdrawn cardWithdrawn = null;
		try {
			cardWithdrawn  = mapper.readValue(kafkaMessage, CardWithdrawn.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        withdrawalRepository.save(Withdrawal.newWithdrawal(UUID.randomUUID(), cardWithdrawn.getAmount(), cardWithdrawn.getCardNo()));
	}
}


