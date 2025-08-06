package com.sccon.pauloazevedo.gestao.strategy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.sccon.pauloazevedo.gestao.utils.GestaoConstants.DIA_REFERENCIA;

public class TempoDeVidaMeses implements TempoDeVida{
    @Override
    public Integer calculaTempo(LocalDate dataPessoa) {
        var diaReferencia = DIA_REFERENCIA;
        Long dias = ChronoUnit.MONTHS.between(dataPessoa, diaReferencia);
        return dias.intValue();
    }
}
