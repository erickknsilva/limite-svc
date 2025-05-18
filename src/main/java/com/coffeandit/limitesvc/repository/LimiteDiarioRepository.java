package com.coffeandit.limitesvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LimiteDiarioRepository
        extends CrudRepository<LimiteDiario, Long> {


    Optional<LimiteDiario> findByAgenciaAndConta(final Long agencia, final Long conta);


    LimiteDiario findByAgenciaAndContaAndData(Long codigoAgencia, Long codigoAgencia1, LocalDateTime data);


}
