package com.sccon.pauloazevedo.gestao.controllers.requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class CriaPessoaRequest {
    private Integer id;
    @NonNull
    private String nome;
    @NonNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    @NonNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeAdmissao;

    public CriaPessoaRequest() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public void setDataDeNascimento(@NonNull LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setDataDeAdmissao(@NonNull LocalDate dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public Integer getId() {
        return id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    @NonNull
    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    @NonNull
    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }
}
