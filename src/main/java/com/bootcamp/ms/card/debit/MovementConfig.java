package com.bootcamp.ms.card.debit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class MovementConfig {

    @Value("${endpoint.movement}")
    private String url;

}
