package com.sccon.pauloazevedo.gestao.strategy;

import java.time.LocalDate;
import java.time.Period;

import static com.sccon.pauloazevedo.gestao.utils.GestaoConstants.DIA_REFERENCIA;

public class TempoDeVidaAnos implements TempoDeVida{
    @Override
    public Integer calculaTempo(LocalDate dataPessoa) {
        var diaReferencia = DIA_REFERENCIA;
        return Period.between(dataPessoa, diaReferencia).getYears();
    }
}
