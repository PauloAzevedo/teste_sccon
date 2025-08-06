package com.sccon.pauloazevedo.gestao.controllers;

import com.sccon.pauloazevedo.gestao.controllers.requests.AtualizaNomePessoaRequest;
import com.sccon.pauloazevedo.gestao.controllers.requests.AtualizaPessoaRequest;
import com.sccon.pauloazevedo.gestao.controllers.requests.CriaPessoaRequest;
import com.sccon.pauloazevedo.gestao.controllers.responses.PessoaResponse;
import com.sccon.pauloazevedo.gestao.exceptions.PessoaJaExisteException;
import com.sccon.pauloazevedo.gestao.exceptions.PessoaNaoEncontradaException;
import com.sccon.pauloazevedo.gestao.exceptions.BadRequestPersonalizada;
import com.sccon.pauloazevedo.gestao.mappers.PessoaMapper;
import com.sccon.pauloazevedo.gestao.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("person")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaMapper pessoaMapper;

    @GetMapping
    public List<PessoaResponse> listaPessoas(){
        var pessoas = pessoaService.listaPessoas();
        return pessoas.stream().map(pessoaMapper::converterPessoaParaResponse).toList();
    }

    @PostMapping
    public ResponseEntity<?> criarPessoa(@RequestBody CriaPessoaRequest pessoaRequest){
        try {
            var pessoa = pessoaService.criaPessoa(pessoaMapper.converterRequestParaPessoa(pessoaRequest));
            return ResponseEntity.ok(pessoaMapper.converterPessoaParaResponse(pessoa));
        } catch (PessoaJaExisteException ex) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaPessoa(@PathVariable Integer id){
        try {
            pessoaService.deletaPessoa(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (PessoaNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaPessoa(@PathVariable Integer id, @RequestBody AtualizaPessoaRequest atualizaPessoaRequest) {
        try {
            var pessoa = pessoaService.atualizaPessoa(id, pessoaMapper.converterAtualizaRequestParaPessoa(atualizaPessoaRequest));
            return ResponseEntity.ok(pessoaMapper.converterPessoaParaResponse(pessoa));
        } catch (PessoaNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizaNomePessoa(@PathVariable Integer id, @RequestBody AtualizaNomePessoaRequest atualizaNomePessoaRequest){
        try {
            var pessoa = pessoaService.atualizaNomePessoa(id, atualizaNomePessoaRequest.getNome());
            return ResponseEntity.ok(pessoaMapper.converterPessoaParaResponse(pessoa));
        } catch (PessoaNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhaPessoa(@PathVariable Integer id){
        try {
            var pessoa = pessoaService.detalhaPessoa(id);
            return ResponseEntity.ok(pessoaMapper.converterPessoaParaResponse(pessoa));
        } catch (PessoaNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @GetMapping("/{id}/age")
    public ResponseEntity<?> mostraIdadePessoa(@PathVariable Integer id, @RequestParam String output){
        try {
            var idade = pessoaService.idadePessoa(id, output);
            return ResponseEntity.ok(idade);
        } catch (PessoaNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (BadRequestPersonalizada ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<?> mostraSalario(@PathVariable Integer id, @RequestParam String output) {
        try {
            var salario = pessoaService.salarioPessoa(id, output);
            return ResponseEntity.ok(salario);
        } catch (PessoaNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (BadRequestPersonalizada ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }
}
