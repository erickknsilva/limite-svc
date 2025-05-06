package com.coffeandit.limitesvc.api;

import com.coffeandit.limitesvc.repository.LimiteDiario;
import com.coffeandit.limitesvc.service.LimiteDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LimiteDiarioController {

    private final LimiteDiarioService limiteDiarioService;

    public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
        this.limiteDiarioService = limiteDiarioService;
    }


    @GetMapping("/limite-diario/{id}")
    public LimiteDiario findById(@PathVariable("id") Long id) {

        var limiteDiario = limiteDiarioService.findById(id);

        if ((limiteDiario.isPresent())){
            return limiteDiario.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
