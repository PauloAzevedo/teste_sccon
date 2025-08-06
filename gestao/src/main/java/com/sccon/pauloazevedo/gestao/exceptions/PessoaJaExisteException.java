package com.sccon.pauloazevedo.gestao.exceptions;

public class PessoaJaExisteException extends RuntimeException{

    public PessoaJaExisteException(String message) {
        super(message);
    }
}
