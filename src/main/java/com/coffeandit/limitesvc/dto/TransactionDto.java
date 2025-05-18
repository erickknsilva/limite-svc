package com.coffeandit.limitesvc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "uuid")
@ToString
public class TransactionDto {

    private UUID uuid;
    private BigDecimal valor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    private Conta conta;

    private BeneficiatioDto beneficiario;

    private TipoTransacao tipoTransacao;

    private SituacaoEnum situacao;

    public void suspeitaFraude() {
        situacao = SituacaoEnum.EM_SUSPEITA_FRAUDE;
    }

    public void analisada() {
        situacao = SituacaoEnum.ANALISADA;
    }

    public void analiseHumana() {
        situacao = SituacaoEnum.EM_ANALISE_HUMANA;
    }


}
