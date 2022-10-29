package com.fabao.operadora.saude.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabao.operadora.saude.dto.AutorizacaoDTO;
import com.fabao.operadora.saude.dto.SolicitacaoDTO;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;

@RestController
@RequestMapping(path = "/solicitacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SolicitacaoController {	
	
	@PostMapping
	AutorizacaoDTO autorizar(@RequestBody SolicitacaoDTO solicitacao) throws BeneficiarioException, ProcedimentoException, AutorizacaoException;

}
