package com.sccon.pauloazevedo.gestao.controllers.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sccon.pauloazevedo.gestao.models.Pessoa;

import java.time.LocalDate;

public class PessoaResponse {
    private Integer id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeAdmissao;

    public PessoaResponse(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataDeNascimento = pessoa.getDataDeNascimento();
        this.dataDeAdmissao = pessoa.getDataDeAdmissao();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }
}
