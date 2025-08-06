package com.sccon.pauloazevedo.gestao.services;

import com.sccon.pauloazevedo.gestao.enums.TipoSalarioEnum;
import com.sccon.pauloazevedo.gestao.exceptions.BadRequestPersonalizada;
import com.sccon.pauloazevedo.gestao.exceptions.PessoaNaoEncontradaException;
import com.sccon.pauloazevedo.gestao.models.Pessoa;
import com.sccon.pauloazevedo.gestao.repositories.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.sccon.pauloazevedo.gestao.utils.GestaoConstants.SALARIO_BASE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private SalarioService salarioService;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        pessoaService = new PessoaService(pessoaRepository,salarioService);
    }


    @Test
    void listaPessoas_deveRetornarOrdenadoPorNome() {
        Pessoa p1 = new Pessoa(1, "Zé", LocalDate.of(1990,1,1), LocalDate.now());
        Pessoa p2 = new Pessoa(2, "Ana", LocalDate.of(1991,1,1), LocalDate.now());
        Pessoa p3 = new Pessoa(3, "Bruno", LocalDate.of(1992,1,1), LocalDate.now());

        when(pessoaRepository.findAll()).thenReturn(List.of(p1, p2, p3));

        List<Pessoa> resultado = pessoaService.listaPessoas();

        assertEquals(3, resultado.size());
        assertEquals("Ana", resultado.get(0).getNome());
        assertEquals("Bruno", resultado.get(1).getNome());
        assertEquals("Zé", resultado.get(2).getNome());
    }

    @Test
    void detalhaPessoa_quandoNaoExistir_deveLancarPessoaNaoEncontradaException() {
        when(pessoaRepository.findById(999)).thenReturn(Optional.empty());
        assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.detalhaPessoa(999));
    }

    @Test
    void validaTempoEnum_invalido_emIdadePessoa_deveLancarBadRequestPersonalizada() {
        assertThrows(BadRequestPersonalizada.class, () -> pessoaService.idadePessoa(11, "invalido"));
    }

    @Test
    void idadePessoa_Sucesso_comTempoValido_deveDelegarParaTempoAtual() {
        var pessoa = new Pessoa(20, "Paulo", LocalDate.of(2000,1,1), LocalDate.of(2020,1,1));

        when(pessoaRepository.findById(20)).thenReturn(Optional.of(pessoa));
        int idade = pessoaService.idadePessoa(20, "years");
        int idadeEsperada = 25;

        assertEquals(idadeEsperada, idade);
    }

    @Test
    void salarioPessoa_Sucesso_deveUsarSalarioService_eRetornarValorArredondado() {
        var pessoa = new Pessoa(20, "Paulo", LocalDate.of(2000,1,1), LocalDate.of(2025,1,1));

        when(pessoaRepository.findById(20)).thenReturn(Optional.of(pessoa));
        when(salarioService.validaTipoSalarioEnum(anyString())).thenReturn(TipoSalarioEnum.MIN);
        when(salarioService.calculaSalarioFull(anyInt(), anyDouble())).thenReturn(1558.00);
        when(salarioService.calcularSalarioMinimo(1558.00)).thenReturn(1.2);
        when(salarioService.arredondarValor(anyDouble())).thenReturn(1.2);

        Double resultado = pessoaService.salarioPessoa(20, "min");

        assertEquals(1.2, resultado);
        verify(salarioService).calculaSalarioFull(anyInt(), eq(SALARIO_BASE));
        verify(salarioService).arredondarValor(1.2);
    }

}
