package io.dddbyexamples.cqrs.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import io.dddbyexamples.cqrs.controller.WithdrawalDto;
import reactor.core.publisher.Flux;

public interface WithdrawalsRepository extends ReactiveCrudRepository<WithdrawalDto, String> {

    Flux<WithdrawalDto> findAllByCard(String card);
}
