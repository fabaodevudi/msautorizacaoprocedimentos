package com.fabao.operadora.saude.service;

import com.fabao.operadora.saude.dto.AutorizacaoDTO;
import com.fabao.operadora.saude.entity.Autorizacao;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AutorizacaoService {
	Autorizacao autorizar(String cpf, String procedimento) throws BeneficiarioException, ProcedimentoException, AutorizacaoException;
	List<Autorizacao> listar (String cpf, String procedimento) throws BeneficiarioException, ProcedimentoException, AutorizacaoException;
}
