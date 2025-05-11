package com.coffeandit.limitesvc.api;

import com.coffeandit.limitesvc.repository.LimiteDiario;
import com.coffeandit.limitesvc.service.LimiteDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RequestMapping("/limite-diario")
@RestController
public class LimiteDiarioController {

    private final LimiteDiarioService limiteDiarioService;

    public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
        this.limiteDiarioService = limiteDiarioService;
    }

    @GetMapping("/{agencia}/{conta}")
    public LimiteDiario findByLimiteDiario(@PathVariable("agencia") final Long agencia,
                                           @PathVariable("conta") final Long conta) {

        Optional<LimiteDiario> limiteDiario = limiteDiarioService.findByLimiteDiario(agencia, conta);

        if (limiteDiario.isPresent()) {
            return limiteDiario.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }

    @GetMapping("/{id}")
    public LimiteDiario findById(@PathVariable("id") Long id) {

        var limiteDiario = limiteDiarioService.findById(id);

        if ((limiteDiario.isPresent())) {
            return limiteDiario.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
