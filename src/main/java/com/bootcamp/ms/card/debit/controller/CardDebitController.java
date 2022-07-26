package com.bootcamp.ms.card.debit.controller;

import com.bootcamp.ms.card.debit.service.BankAccountService;
import com.bootcamp.ms.card.debit.service.CardDebitService;
import com.bootcamp.ms.card.debit.service.MovementsService;
import com.bootcamp.ms.commons.entity.BankAccount;
import com.bootcamp.ms.commons.entity.CardDebit;
import com.bootcamp.ms.commons.entity.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CardDebitController {

    @Autowired
    private CardDebitService cardDebitService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private MovementsService movementsService;

    private final Logger logger = LoggerFactory.getLogger(CardDebitController.class);

    @GetMapping(value = "/all")
    public Flux<CardDebit> getAll(){
        return cardDebitService.findAll();
    }

    @GetMapping(value = "/findBy/{id}")
    public Mono<CardDebit> findBy(@PathVariable String id){
        return cardDebitService.findById(id);
    }

    @PostMapping(value = "/create")
    //@CircuitBreaker(name = "customerContactInfoService", fallbackMethod = "metodoAlternativo")
    public Mono<CardDebit> create(@RequestBody CardDebit cardDebit){

        return bankAccountService.findById(cardDebit.getIdBankAccount())
                .flatMap(b -> {
                    cardDebit.setBankAccount(b);
                    logger.info(b.getAmount().toString());
                    logger.info("XXXXX " + cardDebit.getBankAccount().getAmount());
                    return cardDebitService.save(cardDebit);
                });
    }

    public String metodoAlternativo(){
        return "EL SERVICIO NO SE ENCUENTRA DISPONIBLE";
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> deleteCardDebit(@PathVariable String id) {
        return cardDebitService.findById(id).flatMap(c -> cardDebitService.delete(c));
    }

    @PostMapping(value = "/withdrawal/{id}")
    //@CircuitBreaker(name = "movements", fallbackMethod = "metodoAlternativo")
    public Mono<Movement> withdrawal(@RequestBody Movement movement, @PathVariable String id){

        return cardDebitService.findById(id)
                .flatMap(c -> {
                    bankAccountService.findById(c.getIdBankAccount())
                            .flatMap(b -> {
                                if(b.getAmount() > movement.getAmount()){
                                    double amount = b.getAmount() - movement.getAmount();
                                    logger.info(amount + "");
                                    b.setAmount(amount);
                                    c.setBankAccount(b);
                                    //bankAccountService.save(b).subscribe();
                                    cardDebitService.save(c).subscribe();
                                    return movementsService.save(movement);
                                }
                                return Mono.empty();
                            })
                            .subscribe();

                    return Mono.empty();
                });
    }

}
