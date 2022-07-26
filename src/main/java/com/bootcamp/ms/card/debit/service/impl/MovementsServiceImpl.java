package com.bootcamp.ms.card.debit.service.impl;

import com.bootcamp.ms.card.debit.MovementConfig;
import com.bootcamp.ms.card.debit.service.MovementsService;
import com.bootcamp.ms.commons.entity.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MovementsServiceImpl implements MovementsService {

    @Autowired
    private WebClient client;

    @Autowired
    private MovementConfig movementConfig;

    @Override
    public Mono<Movement> save(Movement movement) {
        return client.post()
                .uri(movementConfig.getUrl().concat("/create"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(movement)
                .retrieve()
                .bodyToMono(Movement.class);
    }
}
