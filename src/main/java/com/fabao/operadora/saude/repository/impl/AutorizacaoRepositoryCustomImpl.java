package com.fabao.operadora.saude.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.fabao.operadora.saude.entity.Autorizacao;
import com.fabao.operadora.saude.entity.Beneficiario;
import com.fabao.operadora.saude.entity.Procedimento;
import com.fabao.operadora.saude.repository.AutorizacaoRepositoryCustom;

@Repository
public class AutorizacaoRepositoryCustomImpl implements AutorizacaoRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

    @Override
    public List<Autorizacao> findAutorizacoesByCpfAndNomeProcedimento(String cpf, String nomeProcedimento) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Autorizacao> query = builder.createQuery(Autorizacao.class);

        Root<Autorizacao> autorizacoes = query.from(Autorizacao.class);
        Join<Autorizacao, Beneficiario> beneficiario = autorizacoes.join("beneficiario");
        Join<Autorizacao, Procedimento> procedimento = autorizacoes.join("procedimento");

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(cpf)) {
            predicates.add(builder.equal(beneficiario.get("cpf"), cpf));
        }

        if (StringUtils.isNotEmpty(nomeProcedimento)) {
            predicates.add(builder.equal(procedimento.get("nome"), nomeProcedimento));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(query).getResultList();
    }
}
