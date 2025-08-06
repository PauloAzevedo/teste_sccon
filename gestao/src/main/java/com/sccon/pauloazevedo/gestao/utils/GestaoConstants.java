package com.sccon.pauloazevedo.gestao.utils;

import java.time.LocalDate;

public class GestaoConstants {

    public static final String MENSAGEM_CONFLITO = "Pessoa com ID já cadastrado.";
    public static final String MENSAGEM_PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada.";
    public static final String MENSAGEM_BAD_REQUEST_TIPO_SALARIO = "Output deve ser uma das opções: min, full";
    public static final String MENSAGEM_BAD_REQUEST_TEMPO = "Output deve ser uma das opções: days, months, years";
    public static final Double SALARIO_BASE = 1558.00;
    public static final Double SALARIO_MINIMO = 1302.00;
    public static final Double PERCENTUAL_ACRESCIMO = 0.18;
    public static final Double BONUS_ACRESCIMO = 500.00;
    public static final LocalDate DIA_REFERENCIA = LocalDate.now();

}
