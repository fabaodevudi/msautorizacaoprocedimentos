package com.fabao.operadora.saude;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabao.operadora.saude.builders.BeneficiarioBuilder;
import com.fabao.operadora.saude.builders.ProcedimentoBuilder;
import com.fabao.operadora.saude.builders.RegraBuilder;
import com.fabao.operadora.saude.entity.Autorizacao;
import com.fabao.operadora.saude.entity.Beneficiario;
import com.fabao.operadora.saude.entity.Procedimento;
import com.fabao.operadora.saude.entity.Regra;
import com.fabao.operadora.saude.exception.AutorizacaoException;
import com.fabao.operadora.saude.exception.BeneficiarioException;
import com.fabao.operadora.saude.exception.ProcedimentoException;
import com.fabao.operadora.saude.repository.AutorizacaoRepository;
import com.fabao.operadora.saude.repository.BeneficiarioRepository;
import com.fabao.operadora.saude.repository.ProcedimentoRepository;
import com.fabao.operadora.saude.repository.RegraRepository;
import com.fabao.operadora.saude.service.impl.AutorizacaoServiceImpl;

@RunWith(SpringRunner.class)
public class SolicitacaoServiceTest {
	
		
	@InjectMocks
	private AutorizacaoServiceImpl service;
	
	@Mock
	private ProcedimentoRepository procedimentoRep;
	
	@Mock
	private BeneficiarioRepository beneficiarioRep;
	
	@Mock
	private RegraRepository regraRep;
	
	@Mock
	private AutorizacaoRepository rep;
	
	@Captor
	ArgumentCaptor<Autorizacao> autCaptor;
		
	@Rule
	public ErrorCollector error = new ErrorCollector();		


	@Before
	public void setup(){		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void naodeveAutorizarComBeneficiarioNaoCadastrado() {
		// Cenario
		Beneficiario beneficiario = new Beneficiario();
		beneficiario.setCpf("12345678932");
		beneficiario.setIdade(50);
		
		Procedimento proc1234 = ProcedimentoBuilder.procedimento1234().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc1234Idade20SexoM().getRegra());
		
		when(regraRep.findByProcedimento(proc1234)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(beneficiario.getCpf())).thenReturn(Optional.of(beneficiario));
		when(procedimentoRep.findByNome(proc1234.getNome())).thenReturn(Optional.of(proc1234));
				
		//Acao
		
		Autorizacao autorizacao = null;
		try {
			autorizacao = service.autorizar(beneficiario.getCpf(), proc1234.getNome());			
		
		} catch (BeneficiarioException e) {
			Assert.assertThat(e.getMessage(), is("Beneficiario não encontrado"));
		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			
		}		
		
	}
	
	@Test
	public void deveAutorizarProcedimento1234ParaFabao() {
		// Cenario
		Beneficiario fabao = BeneficiarioBuilder.beneficiarioFabao().getBeneficiario();
		Procedimento proc1234 = ProcedimentoBuilder.procedimento1234().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc1234Idade20SexoM().getRegra());
		
		when(regraRep.findByProcedimento(proc1234)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(fabao.getCpf())).thenReturn(Optional.of(fabao));
		when(procedimentoRep.findByNome(proc1234.getNome())).thenReturn(Optional.of(proc1234));
				
		//Acao
		
		Autorizacao autorizacao = null;
		try {
			autorizacao = service.autorizar(fabao.getCpf(), proc1234.getNome());			
		
		} catch (BeneficiarioException e) {

		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			
		}
		
		// Verificacao
		
		Mockito.verify(rep).save(autCaptor.capture());
		
		autorizacao = autCaptor.getValue();
		
		assertThat(autorizacao.getDataAutorizacao()).isNotNull();
		assertThat(autorizacao.getStatus()).isNotNull();
		
	}
	
	@Test
	public void naoDeveAutorizarProcedimento1234ParaAna() {
		// Cenario
		Beneficiario ana = BeneficiarioBuilder.beneficiarioAna().getBeneficiario();
		Procedimento proc1234 = ProcedimentoBuilder.procedimento1234().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc1234Idade20SexoM().getRegra());
		
		when(regraRep.findByProcedimento(proc1234)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(ana.getCpf())).thenReturn(Optional.of(ana));
		when(procedimentoRep.findByNome(proc1234.getNome())).thenReturn(Optional.of(proc1234));
				
		//Acao
		
		Autorizacao autorizacao = null;
		
		try {
			autorizacao = service.autorizar(ana.getCpf(), proc1234.getNome());
			//Assert.fail();
		} catch (BeneficiarioException e) {
			
		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			Assert.assertThat(e.getMessage(), is("Procedimento não autorizado para este beneficiário. Entre em contato com a seguradora."));
		}		

	}
	
	@Test
	public void deveAutorizarProcedimento4567ParaFabao() {
		// Cenario
		Beneficiario fabao = BeneficiarioBuilder.beneficiarioFabao().getBeneficiario();
		Procedimento proc4567 = ProcedimentoBuilder.procedimento4567().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc4567Idade20SexoM().getRegra(), RegraBuilder.regraProc4567Idade30SexoF().getRegra());
		
		when(regraRep.findByProcedimento(proc4567)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(fabao.getCpf())).thenReturn(Optional.of(fabao));
		when(procedimentoRep.findByNome(proc4567.getNome())).thenReturn(Optional.of(proc4567));
				
		//Acao
		
		Autorizacao autorizacao = null;
		try {
			autorizacao = service.autorizar(fabao.getCpf(), proc4567.getNome());			
			
		} catch (BeneficiarioException e) {
			
		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			
		}
		
		// Verificacao
		
		Mockito.verify(rep).save(autCaptor.capture());
		
		autorizacao = autCaptor.getValue();
		
		assertThat(autorizacao.getDataAutorizacao()).isNotNull();
		assertThat(autorizacao.getStatus()).isNotNull();

	}
	
	@Test
	public void deveAutorizarProcedimento4567ParaRicardao() {
		// Cenario
		Beneficiario ricardao = BeneficiarioBuilder.beneficiarioRicardao().getBeneficiario();
		Procedimento proc4567 = ProcedimentoBuilder.procedimento4567().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc4567Idade20SexoM().getRegra(), RegraBuilder.regraProc4567Idade30SexoF().getRegra());
		
		when(regraRep.findByProcedimento(proc4567)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(ricardao.getCpf())).thenReturn(Optional.of(ricardao));
		when(procedimentoRep.findByNome(proc4567.getNome())).thenReturn(Optional.of(proc4567));
				
		//Acao
		
		Autorizacao autorizacao = null;
		try {
			autorizacao = service.autorizar(ricardao.getCpf(), proc4567.getNome());			
		} catch (BeneficiarioException e) {
			
		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			
		}
		
		// Verificacao
		
		Mockito.verify(rep).save(autCaptor.capture());
		
		autorizacao = autCaptor.getValue();
		
		assertThat(autorizacao.getDataAutorizacao()).isNotNull();
		assertThat(autorizacao.getStatus()).isNotNull();

	}
	
	@Test
	public void deveAutorizarProcedimento4567ParaAna() {
		// Cenario
		Beneficiario ana = BeneficiarioBuilder.beneficiarioAna().getBeneficiario();
		Procedimento proc4567 = ProcedimentoBuilder.procedimento4567().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc4567Idade20SexoM().getRegra(), RegraBuilder.regraProc4567Idade30SexoF().getRegra());
		
		when(regraRep.findByProcedimento(proc4567)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(ana.getCpf())).thenReturn(Optional.of(ana));
		when(procedimentoRep.findByNome(proc4567.getNome())).thenReturn(Optional.of(proc4567));
				
		//Acao
		
		Autorizacao autorizacao = null;
		try {
			autorizacao = service.autorizar(ana.getCpf(), proc4567.getNome());			
		} catch (BeneficiarioException e) {
			
		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			
		}
		
		// Verificacao
		
		Mockito.verify(rep).save(autCaptor.capture());
		
		autorizacao = autCaptor.getValue();
		
		assertThat(autorizacao.getDataAutorizacao()).isNotNull();
		assertThat(autorizacao.getStatus()).isNotNull();

	}
	
	@Test
	public void naoDeveAutorizarProcedimento4567ParaJoao() {
		// Cenario
		Beneficiario joao = BeneficiarioBuilder.beneficiarioJoao().getBeneficiario();
		Procedimento proc4567 = ProcedimentoBuilder.procedimento4567().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc4567Idade20SexoM().getRegra());
		
		when(regraRep.findByProcedimento(proc4567)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(joao.getCpf())).thenReturn(Optional.of(joao));
		when(procedimentoRep.findByNome(proc4567.getNome())).thenReturn(Optional.of(proc4567));
				
		//Acao
		
		Autorizacao autorizacao = null;
		
		try {
			autorizacao = service.autorizar(joao.getCpf(), proc4567.getNome());
			//Assert.fail();
		} catch (BeneficiarioException e) {
			
		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			// Verificacao
			Assert.assertThat(e.getMessage(), is("Procedimento não autorizado para este beneficiário. Entre em contato com a seguradora."));
		}
	}
	
	
	
	@Test
	public void naoDeveAutorizarProcedimento6789ParaAna() {
		Beneficiario ana = BeneficiarioBuilder.beneficiarioAna().getBeneficiario();
		Procedimento proc6789 = ProcedimentoBuilder.procedimento1234().getProcedimento();
		List<Regra> regras =  Arrays.asList(RegraBuilder.regraProc1234Idade20SexoM().getRegra());
		
		when(regraRep.findByProcedimento(proc6789)).thenReturn(regras);
		when(beneficiarioRep.findByCpf(ana.getCpf())).thenReturn(Optional.of(ana));
		when(procedimentoRep.findByNome(proc6789.getNome())).thenReturn(Optional.of(proc6789));
				
		//Acao
		
		Autorizacao autorizacao = null;
		
		try {
			autorizacao = service.autorizar(ana.getCpf(), proc6789.getNome());
			//Assert.fail();
		} catch (BeneficiarioException e) {
			
		} catch (ProcedimentoException e) {
			
		} catch (AutorizacaoException e) {
			// Verificacao
			Assert.assertThat(e.getMessage(), is("Procedimento não autorizado para este beneficiário. Entre em contato com a seguradora."));
		}	

	}
		
}
