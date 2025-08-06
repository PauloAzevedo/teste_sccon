package com.sccon.pauloazevedo.gestao.mappers;

import com.sccon.pauloazevedo.gestao.controllers.requests.AtualizaPessoaRequest;
import com.sccon.pauloazevedo.gestao.controllers.requests.CriaPessoaRequest;
import com.sccon.pauloazevedo.gestao.controllers.responses.PessoaResponse;
import com.sccon.pauloazevedo.gestao.models.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    public PessoaResponse converterPessoaParaResponse(Pessoa pessoa) {
        return new PessoaResponse(pessoa);
    }

    public Pessoa converterRequestParaPessoa(CriaPessoaRequest pessoaRequest) {
        return new Pessoa(
                pessoaRequest.getId(),
                pessoaRequest.getNome(),
                pessoaRequest.getDataDeNascimento(),
                pessoaRequest.getDataDeAdmissao()
        );
    }

    public Pessoa converterAtualizaRequestParaPessoa(AtualizaPessoaRequest pessoaRequest) {
        return new Pessoa(
                null,
                pessoaRequest.getNome(),
                pessoaRequest.getDataDeNascimento(),
                pessoaRequest.getDataDeAdmissao()
        );
    }
}
