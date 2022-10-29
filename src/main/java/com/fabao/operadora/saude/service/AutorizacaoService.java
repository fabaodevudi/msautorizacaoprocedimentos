package com.fabao.operadora.saude.service;

import org.springframework.stereotype.Service;

import com.fabao.operadora.saude.entity.Autorizacao;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;

@Service
public interface AutorizacaoService {
	Autorizacao autorizar(String cpf, String procedimento) throws BeneficiarioException, ProcedimentoException, AutorizacaoException;

}
