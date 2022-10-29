package com.fabao.operadora.saude.builders;

import com.fabao.operadora.saude.entity.Regra;
import com.fabao.operadora.saude.enums.Sexo;

public class RegraBuilder {
	
	private Regra regra;

	public static RegraBuilder regraProc1234Idade20SexoM() {
		RegraBuilder builder = new RegraBuilder();
		builder.regra = new Regra();
		builder.regra.setId(1L);
		builder.regra.setProcedimento(ProcedimentoBuilder.procedimento1234().getProcedimento());
		builder.regra.setIdade(20);
		builder.regra.setSexo(Sexo.M);	
		return builder;		
	}
	
	public static RegraBuilder regraProc4567Idade20SexoM() {
		RegraBuilder builder = new RegraBuilder();
		builder.regra = new Regra();
		builder.regra.setId(2L);
		builder.regra.setProcedimento(ProcedimentoBuilder.procedimento4567().getProcedimento());
		builder.regra.setIdade(20);
		builder.regra.setSexo(Sexo.M);	
		return builder;		
	}
	
	public static RegraBuilder regraProc6789Idade10SexoM() {
		RegraBuilder builder = new RegraBuilder();
		builder.regra = new Regra();
		builder.regra.setId(3L);
		builder.regra.setProcedimento(ProcedimentoBuilder.procedimento6789().getProcedimento());
		builder.regra.setIdade(10);
		builder.regra.setSexo(Sexo.M);	
		return builder;		
	}
	
	public static RegraBuilder regraProc4567Idade30SexoF() {
		RegraBuilder builder = new RegraBuilder();
		builder.regra = new Regra();
		builder.regra.setId(3L);
		builder.regra.setProcedimento(ProcedimentoBuilder.procedimento4567().getProcedimento());
		builder.regra.setIdade(30);
		builder.regra.setSexo(Sexo.F);	
		return builder;		
	}
	
	public Regra getRegra() {
		return regra;		
	}	
	
}
