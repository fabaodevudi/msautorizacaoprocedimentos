package com.fabao.operadora.saude.repository;

import com.fabao.operadora.saude.entity.Autorizacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacaoRepository extends CrudRepository<Autorizacao, Integer>, AutorizacaoRepositoryCustom {
}
