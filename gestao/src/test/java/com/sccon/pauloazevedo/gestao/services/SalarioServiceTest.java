package com.sccon.pauloazevedo.gestao.services;


import com.sccon.pauloazevedo.gestao.enums.TipoSalarioEnum;
import com.sccon.pauloazevedo.gestao.exceptions.BadRequestPersonalizada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalarioServiceTest {

    private SalarioService service;

    @BeforeEach
    void setUp() {
        service = new SalarioService();
    }

    @Test
    void validaTipoSalarioEnum_Sucess() {
        String valido = "full";
        TipoSalarioEnum tipoSalarioEnum = TipoSalarioEnum.FULL;
        var resultado = service.validaTipoSalarioEnum(valido.toUpperCase());
        assertEquals(tipoSalarioEnum, resultado);
    }

    @Test
    void validaTipoSalarioEnum_BadRequest() {
        String invalido = "fullinvalido";
        assertThrows(BadRequestPersonalizada.class, () -> service.validaTipoSalarioEnum(invalido));
    }

    @Test
    void calculaSalarioFull_SucessoUmAno() {
        int tempoServico = 1;
        double salarioInicial = 1558.00;
        Double resultado = service.calculaSalarioFull(tempoServico, salarioInicial);
        double esperado = 2338.45;
        assertEquals(esperado, service.arredondarValor(resultado));
    }

    @Test
    void calculaSalarioFull_SucessoCincoAnos() {
        int tempoServico = 5;
        double salarioInicial = 1558.00;
        Double resultado = service.calculaSalarioFull(tempoServico, salarioInicial);
        double esperado = 7141.44;
        assertEquals(esperado, service.arredondarValor(resultado));
    }

    @Test
    void calcularSalario_SucessoMinimoUmAno() {
        int tempoServico = 1;
        double salarioInicial = 1558.00;
        double resultado = service.calculaSalarioFull(tempoServico, salarioInicial);
        double salarioMinimo = service.calcularSalarioMinimo(resultado);
        double esperado = 1.8;
        assertEquals(esperado, service.arredondarValor(salarioMinimo));
    }

    @Test
    void calcularSalario_SucessoMinimoCincoAnos() {
        int tempoServico = 5;
        double salarioInicial = 1558.00;
        double resultado = service.calculaSalarioFull(tempoServico, salarioInicial);
        double salarioMinimo = service.calcularSalarioMinimo(resultado);
        double esperado = 5.49;
        assertEquals(esperado, service.arredondarValor(salarioMinimo));
    }

    @Test
    void arredondarValor_Sucesso() {
        double valor = 2156.3333;
        double resultado = service.arredondarValor(valor);
        double esperado = 2156.34;
        assertEquals(esperado, resultado);
    }
}