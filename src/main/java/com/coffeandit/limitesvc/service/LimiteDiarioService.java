package com.coffeandit.limitesvc.service;


import com.coffeandit.limitesvc.dto.TransactionDto;
import com.coffeandit.limitesvc.events.LimiteProducer;
import com.coffeandit.limitesvc.repository.LimiteDiario;
import com.coffeandit.limitesvc.repository.LimiteDiarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class LimiteDiarioService {

    @Value("${limite.valor:0}")
    private BigDecimal valor;

    private final LimiteDiarioRepository limiteDiarioRepository;
    private final LimiteProducer limiteProducer;

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository, LimiteProducer limiteProducer) {
        this.limiteDiarioRepository = limiteDiarioRepository;
        this.limiteProducer = limiteProducer;
    }

    public Optional<LimiteDiario> findById(Long id) {
        return limiteDiarioRepository.findById(id);
    }

    public Optional<LimiteDiario> findByLimiteDiario(final Long agencia, final Long conta) {

        final Optional<LimiteDiario> limiteDiario = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);

        if (limiteDiario.isPresent()) {
            return limiteDiario;
        }
        var limit = createLimiteDiario(agencia, conta);
        return Optional.of(limiteDiarioRepository.save(limit));
    }

    private LimiteDiario createLimiteDiario(Long agencia, Long conta) {
        var limite = new LimiteDiario();
        limite.setValor(valor);
        limite.setConta(conta);
        limite.setAgencia(agencia);
        limite.setData(LocalDate.now());
        return limite;
    }

    public void validarLimiteDiario(TransactionDto transactionDto) {
        var limiteDiario = limiteDiarioRepository.findByAgenciaAndContaAndData(
                transactionDto.getConta().getCodigoAgencia(), transactionDto.getConta().getCodigoConta(), LocalDate.now()
        );

        if (Objects.isNull(limiteDiario)) {
            limiteDiario = getLimiteDiario(transactionDto);
        }

        if (limiteDiario.getValor().compareTo(transactionDto.getValor()) < 0) {
            transactionDto.suspeitaFraude();
            log.info("Transação excede valor diário: {}", transactionDto);
        }

        else if (limiteDiario.getValor().compareTo(BigDecimal.valueOf(100000l)) > 0) {
            transactionDto.analiseHumana();
            log.info("Transação está em analise humana: {}", transactionDto);
        }

        else {
            transactionDto.analisada();
            log.info("Transação está analisada");
            limiteDiario.setValor(limiteDiario.getValor().subtract(transactionDto.getValor()));
            limiteDiarioRepository.save(limiteDiario);
        }

        limiteProducer.enviar(transactionDto);
    }

    private LimiteDiario getLimiteDiario(TransactionDto transactionDto) {
        LimiteDiario limiteDiario;
        limiteDiario = new LimiteDiario();
        limiteDiario.setAgencia(transactionDto.getConta().getCodigoAgencia());
        limiteDiario.setConta(transactionDto.getConta().getCodigoConta());
        limiteDiario.setValor(valor);
        limiteDiario.setData(transactionDto.getData());
        limiteDiario = limiteDiarioRepository.save(limiteDiario);
        return limiteDiario;
    }


    private void createLimiteDiarioParaValidacao(TransactionDto transactionDto) {
        LimiteDiario limiteDiario = new LimiteDiario();

        limiteDiario.setAgencia(transactionDto.getConta().getCodigoAgencia());
        limiteDiario.setConta(transactionDto.getConta().getCodigoConta());
        limiteDiario.setValor(valor);
        limiteDiario.setData(transactionDto.getData());
        limiteDiario = limiteDiarioRepository.save(limiteDiario);
    }

}
