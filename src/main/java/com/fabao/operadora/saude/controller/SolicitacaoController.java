package com.fabao.operadora.saude.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabao.operadora.saude.dto.SolicitacaoDTO;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;

@RestController
@RequestMapping(path = "/solicitacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SolicitacaoController {	
	
	@PostMapping
	ResponseEntity<?> autorizar(@RequestBody SolicitacaoDTO solicitacao) throws BeneficiarioException, ProcedimentoException, AutorizacaoException;

	@GetMapping
	ResponseEntity<?> listar(@RequestParam(name = "cpf", required = false) String cpf, @RequestParam(name = "nomeProcedimento", required = false) String nomeProcedimento) throws BeneficiarioException, ProcedimentoException, AutorizacaoException;

}
