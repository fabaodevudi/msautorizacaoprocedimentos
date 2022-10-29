package com.fabao.operadora.saude.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabao.operadora.saude.entity.Autorizacao;

@Repository
public interface AutorizacaoRepository extends CrudRepository<Autorizacao, Integer> {
	
}
