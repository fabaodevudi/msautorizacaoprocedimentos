package com.fabao.operadora.saude.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.fabao.operadora.saude.utils.DataUtil;

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
		String hoje = DataUtil.converterDateToString(new Date(), "dd/MM/yyyy");
		
		List<Autorizacao> listaAutorizacao = rep.findAutorizacoesByCpfAndNomeProcedimento(cpf, nomeProcedimento);
		
		listaAutorizacao.forEach(item -> {
			if (DataUtil.converterDateToString(item.getDataAutorizacao(), "dd/MM/yyyy").equals(hoje)) {
				throw new AutorizacaoException("Não é permitido o mesmo procedimento para o mesmo dia");
			}
		});		
		
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setBeneficiario(beneficiario);
		autorizacao.setDataAutorizacao(new Date());
		autorizacao.setProcedimento(procedimento);
		autorizacao.setStatus(Status.A);
		autorizacao = rep.save(autorizacao);		
		
		return autorizacao;
	}

    @Override
    public List<Autorizacao> listar(String cpf, String nomeProcedimento) throws BeneficiarioException, ProcedimentoException, AutorizacaoException {    	
    	
    	if(StringUtils.isNotEmpty(cpf) && !beneficiarioRep.findByCpf(cpf).isPresent()) {
    		throw new ProcedimentoException("Beneficiário não encontrado");

    	}
    	
    	if(StringUtils.isNotEmpty(nomeProcedimento) && !procedimentoRep.findByNome(nomeProcedimento).isPresent()) {
    		throw new ProcedimentoException("Procedimento não encontrado");
    	}
    	    	
		return rep.findAutorizacoesByCpfAndNomeProcedimento(cpf, nomeProcedimento);		
		
    }

}
