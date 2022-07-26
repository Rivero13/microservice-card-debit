package com.bootcamp.ms.card.debit.service.impl;

import com.bootcamp.ms.card.debit.repository.CardDebitRepository;
import com.bootcamp.ms.card.debit.service.CardDebitService;
import com.bootcamp.ms.commons.entity.CardDebit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardDebitServiceImpl implements CardDebitService {

    @Autowired
    private CardDebitRepository cardDebitRepository;

    @Override
    public Flux<CardDebit> findAll() {
        return cardDebitRepository.findAll();
    }

    @Override
    public Mono<CardDebit> findById(String id) {
        return cardDebitRepository.findById(id);
    }

    @Override
    public Mono<CardDebit> save(CardDebit cardDebit) {
        return cardDebitRepository.save(cardDebit);
    }

    @Override
    public Mono<Void> delete(CardDebit cardDebit) {
        return cardDebitRepository.delete(cardDebit);
    }
}
