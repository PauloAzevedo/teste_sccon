package com.sccon.pauloazevedo.gestao.repositories;

import com.sccon.pauloazevedo.gestao.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("SELECT max(id) FROM Pessoa")
    public int maximoIdUtilizado();
}
