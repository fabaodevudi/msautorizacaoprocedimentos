package com.fabao.operadora.saude.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fabao.operadora.saude.controller.SolicitacaoController;
import com.fabao.operadora.saude.dto.AutorizacaoDTO;
import com.fabao.operadora.saude.dto.SolicitacaoDTO;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;
import com.fabao.operadora.saude.service.AutorizacaoService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SolicitacaoControllerImpl implements SolicitacaoController {

	@Autowired
	AutorizacaoService service;

	@Override
	public ResponseEntity<?> autorizar(SolicitacaoDTO solicitacao) throws BeneficiarioException, ProcedimentoException, AutorizacaoException {		
		return  new ResponseEntity<>(AutorizacaoDTO.fromEntity(service.autorizar(solicitacao.getCpf(), solicitacao.getNomeProcedimento())), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> listar(String cpf, String nomeProcedimento) throws BeneficiarioException, ProcedimentoException, AutorizacaoException {		
		List<AutorizacaoDTO> lista = service.listar(cpf, nomeProcedimento).stream().map(item -> AutorizacaoDTO.fromEntity(item)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK)  ;
	}


}
