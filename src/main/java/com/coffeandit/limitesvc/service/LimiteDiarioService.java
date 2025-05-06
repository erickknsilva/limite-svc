package com.coffeandit.limitesvc.service;


import com.coffeandit.limitesvc.repository.LimiteDiario;
import com.coffeandit.limitesvc.repository.LimiteDiarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimiteDiarioService {

    private final LimiteDiarioRepository limiteDiarioRepository;

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
        this.limiteDiarioRepository = limiteDiarioRepository;
    }

    public Optional<LimiteDiario> findById(Long id) {
        return limiteDiarioRepository.findById(id);
    }

}
