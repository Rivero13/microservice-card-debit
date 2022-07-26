package com.bootcamp.ms.card.debit.service;

import com.bootcamp.ms.commons.entity.CardDebit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardDebitService {

    public Flux<CardDebit> findAll();

    public Mono<CardDebit> findById(String id);

    public Mono<CardDebit> save(CardDebit cardDebit);

    public Mono<Void> delete(CardDebit cardDebit);
}
