package com.sccon.pauloazevedo.gestao.controllers.requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class AtualizaPessoaRequest {

    @NonNull
    private String nome;
    @NonNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    @NonNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeAdmissao;

    public AtualizaPessoaRequest() {
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(@NonNull LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @NonNull
    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(@NonNull LocalDate dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }
}
