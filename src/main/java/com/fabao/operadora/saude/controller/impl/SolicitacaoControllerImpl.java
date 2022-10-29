package com.fabao.operadora.saude.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fabao.operadora.saude.controller.SolicitacaoController;
import com.fabao.operadora.saude.dto.AutorizacaoDTO;
import com.fabao.operadora.saude.dto.SolicitacaoDTO;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;
import com.fabao.operadora.saude.service.AutorizacaoService;

@Controller
public class SolicitacaoControllerImpl implements SolicitacaoController {

	@Autowired
	AutorizacaoService service;

	@Override
	public AutorizacaoDTO autorizar(SolicitacaoDTO solicitacao) throws BeneficiarioException, ProcedimentoException, AutorizacaoException {
		return  AutorizacaoDTO.fromEntity(service.autorizar(solicitacao.getCpf(), solicitacao.getNomeProcedimento()));
		
	}
	
		

}
