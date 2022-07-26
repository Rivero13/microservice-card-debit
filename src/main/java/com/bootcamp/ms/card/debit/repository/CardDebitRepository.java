package com.bootcamp.ms.card.debit.repository;

import com.bootcamp.ms.commons.entity.CardDebit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CardDebitRepository extends ReactiveMongoRepository<CardDebit, String> {
}
