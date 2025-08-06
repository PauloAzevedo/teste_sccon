package com.sccon.pauloazevedo.gestao.services;

import com.sccon.pauloazevedo.gestao.enums.TempoEnum;
import com.sccon.pauloazevedo.gestao.enums.TipoSalarioEnum;
import com.sccon.pauloazevedo.gestao.exceptions.BadRequestPersonalizada;
import com.sccon.pauloazevedo.gestao.exceptions.PessoaJaExisteException;
import com.sccon.pauloazevedo.gestao.exceptions.PessoaNaoEncontradaException;
import com.sccon.pauloazevedo.gestao.models.Pessoa;
import com.sccon.pauloazevedo.gestao.repositories.PessoaRepository;
import com.sccon.pauloazevedo.gestao.strategy.TempoDeVidaAnos;
import com.sccon.pauloazevedo.gestao.strategy.TempoDeVidaDias;
import com.sccon.pauloazevedo.gestao.strategy.TempoDeVidaMeses;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static com.sccon.pauloazevedo.gestao.enums.TipoSalarioEnum.MIN;
import static com.sccon.pauloazevedo.gestao.utils.GestaoConstants.*;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final SalarioService salarioService;

    public PessoaService(PessoaRepository pessoaRepository, SalarioService salarioService) {
        this.pessoaRepository = pessoaRepository;
        this.salarioService = salarioService;
    }

    public List<Pessoa> listaPessoas(){
        var pessoas = pessoaRepository.findAll();
        if(!pessoas.isEmpty()){
            return pessoas
                    .stream()
                    .sorted(Comparator.comparing(Pessoa::getNome))
                    .toList();
        }
        return List.of();
    }

    public Pessoa criaPessoa(Pessoa pessoa) {
        if(pessoa.getId()!=null) {
            var pessoaBD = pessoaRepository.findById(pessoa.getId());
            if(pessoaBD.isPresent()){
                throw new PessoaJaExisteException(MENSAGEM_CONFLITO);
            }
        }
        if(pessoa.getId()==null){
            var maxId = pessoaRepository.maximoIdUtilizado();
            pessoa.setId(++maxId);
        }
        pessoaRepository.save(pessoa);
        return pessoa;
    }

    public void deletaPessoa(Integer id){
        var pessoa = detalhaPessoa(id);
        pessoaRepository.delete(pessoa);
    }

    public Pessoa atualizaPessoa(Integer id, Pessoa pessoa){
        var pessoaAtualizavel = detalhaPessoa(id);
        pessoaAtualizavel.setNome(pessoa.getNome());
        pessoaAtualizavel.setDataDeNascimento(pessoa.getDataDeNascimento());
        pessoaAtualizavel.setDataDeAdmissao(pessoa.getDataDeAdmissao());
        pessoaRepository.save(pessoaAtualizavel);
        return pessoaAtualizavel;
    }

    public Pessoa atualizaNomePessoa(Integer id, String novoNome){
        var pessoaAtualizavel = detalhaPessoa(id);
        pessoaAtualizavel.setNome(novoNome);
        pessoaRepository.save(pessoaAtualizavel);
        return pessoaAtualizavel;
    }

    public Pessoa detalhaPessoa(Integer id) {
        var pessoaDB = pessoaRepository.findById(id);
        if(pessoaDB.isPresent()){
            return pessoaDB.get();
        }
        throw new PessoaNaoEncontradaException(MENSAGEM_PESSOA_NAO_ENCONTRADA);
    }

    public Integer idadePessoa(Integer id, String output){
        TempoEnum tempoEnum = validaTempoEnum(output);
        var pessoaDB = detalhaPessoa(id);
        return tempoAtual(pessoaDB.getDataDeNascimento(), tempoEnum);
    }

    public TempoEnum validaTempoEnum(String output) {
        try {
            return TempoEnum.valueOf(output.toUpperCase());
        } catch (Exception e) {
            throw new BadRequestPersonalizada(MENSAGEM_BAD_REQUEST_TEMPO);
        }
    }

    public Integer tempoAtual(LocalDate data, TempoEnum tempoEnum) {
        return switch (tempoEnum) {
            case DAYS -> new TempoDeVidaDias().calculaTempo(data);
            case MONTHS -> new TempoDeVidaMeses().calculaTempo(data);
            default -> new TempoDeVidaAnos().calculaTempo(data);
        };
    }


    public Double salarioPessoa(Integer id, String output ){
        TipoSalarioEnum tipoSalarioEnum = salarioService.validaTipoSalarioEnum(output);
        var pessoaDB = detalhaPessoa(id);
        var tempoServico = tempoAtual(pessoaDB.getDataDeAdmissao(), TempoEnum.YEARS);
        var valorSalario = salarioService.calculaSalarioFull(tempoServico, SALARIO_BASE);
        if (tipoSalarioEnum == MIN) {
            valorSalario = salarioService.calcularSalarioMinimo(valorSalario);
        }
        return salarioService.arredondarValor(valorSalario);
    }




}
