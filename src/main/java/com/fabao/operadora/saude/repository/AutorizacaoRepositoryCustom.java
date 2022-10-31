package com.fabao.operadora.saude.repository;

import com.fabao.operadora.saude.entity.Autorizacao;

import java.util.List;

public interface AutorizacaoRepositoryCustom {
    List<Autorizacao> findAutorizacoesByCpfAndNomeProcedimento(String cpf, String nomeProcedimento);
}
