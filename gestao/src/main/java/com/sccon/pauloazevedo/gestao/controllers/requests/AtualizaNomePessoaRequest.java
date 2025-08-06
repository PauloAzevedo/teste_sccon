package com.sccon.pauloazevedo.gestao.controllers.requests;

import org.springframework.lang.NonNull;

public class AtualizaNomePessoaRequest {

    @NonNull
    private String nome;

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }
}
