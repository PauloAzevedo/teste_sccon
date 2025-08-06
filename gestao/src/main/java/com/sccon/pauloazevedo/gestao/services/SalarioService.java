package com.sccon.pauloazevedo.gestao.services;

import com.sccon.pauloazevedo.gestao.enums.TipoSalarioEnum;
import com.sccon.pauloazevedo.gestao.exceptions.BadRequestPersonalizada;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.sccon.pauloazevedo.gestao.utils.GestaoConstants.*;

@Service
public class SalarioService {

    public TipoSalarioEnum validaTipoSalarioEnum(String output) {
        try {
            return TipoSalarioEnum.valueOf(output.toUpperCase());
        } catch (Exception e) {
            throw new BadRequestPersonalizada(MENSAGEM_BAD_REQUEST_TIPO_SALARIO);
        }
    }

    public Double calculaSalarioFull(Integer tempoServico, Double valorSalario) {
        int controle = 0;
        while(controle < tempoServico){
            valorSalario = valorSalario + ((valorSalario * PERCENTUAL_ACRESCIMO) +  BONUS_ACRESCIMO);
            controle++;
        }
        return valorSalario;
    }

    public Double calcularSalarioMinimo(Double valorSalario){
        return valorSalario / SALARIO_MINIMO;
    }


    public Double arredondarValor(Double valor){
        return new BigDecimal(valor).setScale(2, RoundingMode.CEILING).doubleValue();
    }
}
