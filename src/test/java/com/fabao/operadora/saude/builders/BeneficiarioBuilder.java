package com.fabao.operadora.saude.builders;

import com.fabao.operadora.saude.entity.Beneficiario;
import com.fabao.operadora.saude.enums.Sexo;

public class BeneficiarioBuilder {
	
	private Beneficiario beneficiario;
	
	public static BeneficiarioBuilder beneficiarioAna() {
		BeneficiarioBuilder builder = new BeneficiarioBuilder();
		builder.beneficiario = new Beneficiario();
		builder.beneficiario.setId(1L);
		builder.beneficiario.setCpf("60119651231");
		builder.beneficiario.setNome("Ana");
		builder.beneficiario.setSexo(Sexo.F);
		builder.beneficiario.setIdade(30);
		return builder;
		
	}
	
	public static BeneficiarioBuilder beneficiarioJoao() {
		BeneficiarioBuilder builder = new BeneficiarioBuilder();
		builder.beneficiario = new Beneficiario();
		builder.beneficiario.setId(2L);
		builder.beneficiario.setCpf("70112651631");
		builder.beneficiario.setNome("João");
		builder.beneficiario.setSexo(Sexo.M);
		builder.beneficiario.setIdade(10);
		return builder;
		
	}
	
	public static BeneficiarioBuilder beneficiarioFabao() {
		BeneficiarioBuilder builder = new BeneficiarioBuilder();
		builder.beneficiario = new Beneficiario();
		builder.beneficiario.setId(3L);
		builder.beneficiario.setCpf("15076423187");
		builder.beneficiario.setNome("Fabao");
		builder.beneficiario.setSexo(Sexo.M);
		builder.beneficiario.setIdade(20);
		return builder;
		
	}
	
	public static BeneficiarioBuilder beneficiarioRicardao() {
		BeneficiarioBuilder builder = new BeneficiarioBuilder();
		builder.beneficiario = new Beneficiario();
		builder.beneficiario.setId(4L);
		builder.beneficiario.setCpf("07474748690");
		builder.beneficiario.setNome("Ricardao");
		builder.beneficiario.setSexo(Sexo.M);
		builder.beneficiario.setIdade(20);
		return builder;
		
	}
	
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	

}
