package com.fabao.operadora.saude.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabao.operadora.saude.entity.Procedimento;
import com.fabao.operadora.saude.entity.Regra;

@Repository
public interface RegraRepository extends CrudRepository<Regra, Integer> {

	List<Regra> findByProcedimento(Procedimento procedimento);

}
