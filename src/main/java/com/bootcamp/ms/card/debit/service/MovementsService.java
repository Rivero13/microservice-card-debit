package com.bootcamp.ms.card.debit.service;

import com.bootcamp.ms.commons.entity.Movement;
import reactor.core.publisher.Mono;

public interface MovementsService {

    Mono<Movement> save(Movement movement);
}
