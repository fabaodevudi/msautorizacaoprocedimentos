package com.fabao.operadora.saude.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabao.operadora.saude.entity.Autorizacao;
import com.fabao.operadora.saude.entity.Beneficiario;
import com.fabao.operadora.saude.entity.Procedimento;
import com.fabao.operadora.saude.entity.Regra;
import com.fabao.operadora.saude.enums.Status;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;
import com.fabao.operadora.saude.repository.AutorizacaoRepository;
import com.fabao.operadora.saude.repository.BeneficiarioRepository;
import com.fabao.operadora.saude.repository.ProcedimentoRepository;
import com.fabao.operadora.saude.repository.RegraRepository;
import com.fabao.operadora.saude.service.AutorizacaoService;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {
	
	@Autowired
	ProcedimentoRepository procedimentoRep;
	
	@Autowired
	BeneficiarioRepository beneficiarioRep;
	
	@Autowired
	RegraRepository regraRep;
	
	@Autowired
	AutorizacaoRepository rep;
		
	@Override
	public Autorizacao autorizar(String cpf, String nomeProcedimento) throws BeneficiarioException, ProcedimentoException, AutorizacaoException {
		
		Beneficiario beneficiario = beneficiarioRep.findByCpf(cpf).orElseThrow(() -> new BeneficiarioException("Beneficiario não encontrado"));
		Procedimento procedimento = procedimentoRep.findByNome(nomeProcedimento).orElseThrow(() -> new ProcedimentoException("Procedimento não encontrado"));
		List<Regra> regras = regraRep.findByProcedimento(procedimento);
		
		Boolean temCobertura = regras.stream().anyMatch(item -> item.getIdade().compareTo(beneficiario. getIdade()) == 0 && item.getSexo().compareTo(beneficiario.getSexo()) == 0);
		
		if (!temCobertura) {
			throw new AutorizacaoException("Procedimento não autorizado para este beneficiário. Entre em contato com a seguradora.");
		}
		
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setBeneficiario(beneficiario);
		autorizacao.setDataAutorizacao(new Date());
		autorizacao.setProcedimento(procedimento);
		autorizacao.setStatus(Status.A);
		autorizacao = rep.save(autorizacao);		
		
		return autorizacao;
	}

}
