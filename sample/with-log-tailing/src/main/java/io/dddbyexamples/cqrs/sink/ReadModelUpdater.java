package io.dddbyexamples.cqrs.sink;

import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dddbyexamples.cqrs.model.Withdrawal;
import io.dddbyexamples.cqrs.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
@RequiredArgsConstructor
class ReadModelUpdater {

	private final WithdrawalRepository withdrawalRepository;

	@KafkaListener(topics="dbserver1.inventory.withdrawal")
	public void handle(String kafkaMessage, Acknowledgment acknowledgment) {
		
		ObjectMapper mapper = new ObjectMapper();
		Envelope message = null;
		try {
			message  = mapper.readValue(kafkaMessage, Envelope.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(message.isUpdate()) {
			saveWithdrawalFrom(message);
		}
	}

	private void saveWithdrawalFrom(Envelope message) {
		UUID cardId = UUID.fromString(message.getPayload().getBefore().getId());
		long withdrawalAmount
				= balanceAfter(message) - balanceBefore(message);
		 withdrawalRepository.save(Withdrawal.newWithdrawal(UUID.randomUUID(), withdrawalAmount, cardId));
	}

	private long balanceAfter(Envelope message) {
		return message.getPayload().getAfter().getUsed_limit();
	}

	private long balanceBefore(Envelope message) {
		return message.getPayload().getBefore().getUsed_limit();
	}


}
