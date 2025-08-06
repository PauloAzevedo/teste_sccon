package com.sccon.pauloazevedo.gestao.strategy;

import java.time.LocalDate;
import java.time.Period;

public class TempoDeVidaAnos implements TempoDeVida{
    @Override
    public Integer calculaTempo(LocalDate dataPessoa) {
        var hoje = LocalDate.now();
        return Period.between(dataPessoa, hoje).getYears();
    }
}
