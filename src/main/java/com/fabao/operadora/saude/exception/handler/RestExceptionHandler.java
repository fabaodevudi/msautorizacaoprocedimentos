package com.fabao.operadora.saude.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fabao.operadora.saude.error.SolicitacaoErroDetalhes;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(AutorizacaoException.class)
	public ResponseEntity<?> handleAutorizacaoException(AutorizacaoException ex) {
		SolicitacaoErroDetalhes detalhes = new SolicitacaoErroDetalhes();
		detalhes.setMessage(ex.getMessage());
		detalhes.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<SolicitacaoErroDetalhes>(detalhes, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BeneficiarioException.class)
	public ResponseEntity<?> handleAutorizacaoException(BeneficiarioException ex) {
		SolicitacaoErroDetalhes detalhes = new SolicitacaoErroDetalhes();
		detalhes.setMessage(ex.getMessage());
		detalhes.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<SolicitacaoErroDetalhes>(detalhes, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProcedimentoException.class)
	public ResponseEntity<?> handleProcedimentoException(ProcedimentoException ex) {
		SolicitacaoErroDetalhes detalhes = new SolicitacaoErroDetalhes();
		detalhes.setMessage(ex.getMessage());
		detalhes.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<SolicitacaoErroDetalhes>(detalhes, HttpStatus.NOT_FOUND);
	}

}
