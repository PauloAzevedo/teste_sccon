package com.sccon.pauloazevedo.gestao.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Pessoa {

    @Id
    private Integer id;

    private String nome;

    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;

    @Column(name = "data_de_admissao")
    private LocalDate dataDeAdmissao;

    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, LocalDate dataDeNascimento, LocalDate dataDeAdmissao) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(LocalDate dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }
}
