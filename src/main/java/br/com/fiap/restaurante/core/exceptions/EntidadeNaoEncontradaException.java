package br.com.fiap.restaurante.core.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String entidade, String nomeNaoEncontrado){
        super(entidade+" com nome <"+nomeNaoEncontrado+"> não encontrada!");
    }
}
