package com.fabao.operadora.saude.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabao.operadora.saude.entity.Beneficiario;

@Repository
public interface BeneficiarioRepository extends CrudRepository<Beneficiario, Integer> {

	Optional<Beneficiario> findByCpf(String cpf);
	List<Beneficiario> findAll();
}
