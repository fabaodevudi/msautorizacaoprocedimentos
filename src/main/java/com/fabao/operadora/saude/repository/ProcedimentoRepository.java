package com.fabao.operadora.saude.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabao.operadora.saude.entity.Procedimento;

@Repository
public interface ProcedimentoRepository extends CrudRepository<Procedimento, Integer> {
	 Optional<Procedimento> findByNome(String nome);
}
