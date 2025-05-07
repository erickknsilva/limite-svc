package com.coffeandit.limitesvc.service;


import com.coffeandit.limitesvc.repository.LimiteDiario;
import com.coffeandit.limitesvc.repository.LimiteDiarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LimiteDiarioService {

    @Value("${limite.valor:0}")
    private BigDecimal valor;

    private final LimiteDiarioRepository limiteDiarioRepository;

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
        this.limiteDiarioRepository = limiteDiarioRepository;
    }

    public Optional<LimiteDiario> findById(Long id) {
        return limiteDiarioRepository.findById(id);
    }

    public Optional<LimiteDiario> findByLimiteDiario(final Long agencia, final Long conta) {

        final Optional<LimiteDiario> limiteDiario = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);

        if (limiteDiario.isPresent()) {
            var limit = createLimiteDiario(agencia, conta);
            return Optional.of(limiteDiarioRepository.save(limit));
        }
        return limiteDiario;
    }

    private LimiteDiario createLimiteDiario(Long agencia, Long conta) {
        var limite = new LimiteDiario();
        limite.setValor(valor);
        limite.setConta(conta);
        limite.setAgencia(agencia);
        return limite;
    }


}
