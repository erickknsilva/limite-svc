package com.coffeandit.limitesvc.events;

import com.coffeandit.limitesvc.dto.TransactionDto;
import com.coffeandit.limitesvc.service.LimiteDiarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class LimiteConsumer {

    private final LimiteDiarioService limiteDiarioService;
    private final ObjectMapper objectMapper;

    public LimiteConsumer(LimiteDiarioService limiteDiarioService, ObjectMapper objectMapper) {
        this.limiteDiarioService = limiteDiarioService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${app.topic}", groupId = "${spring.application.name}")
    public void onConsume(final String message) {

        try {
            TransactionDto transactionDto = getTransaction(message);
            limiteDiarioService.validarLimiteDiario(transactionDto);

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }


    }

    private TransactionDto getTransaction(String message) throws JsonProcessingException {

        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);
        transactionDto.setData(LocalDate.now());
        return transactionDto;
    }

}
