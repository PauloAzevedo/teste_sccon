package com.sccon.pauloazevedo.gestao.strategy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TempoDeVidaMeses implements TempoDeVida{
    @Override
    public Integer calculaTempo(LocalDate dataPessoa) {
        var hoje = LocalDate.now();
        Long dias = ChronoUnit.MONTHS.between(dataPessoa, hoje);
        return dias.intValue();
    }
}
