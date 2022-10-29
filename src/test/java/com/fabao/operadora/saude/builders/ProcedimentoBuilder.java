package com.fabao.operadora.saude.builders;

import com.fabao.operadora.saude.entity.Procedimento;

public class ProcedimentoBuilder {
	
private Procedimento procedimento;
	
	public static ProcedimentoBuilder procedimento1234() {
		ProcedimentoBuilder builder = new ProcedimentoBuilder();		
		builder.procedimento = new Procedimento();
		builder.procedimento.setId(1L);
		builder.procedimento.setNome("1234");		
		return builder;		
	}
	
	public static ProcedimentoBuilder procedimento4567() {
		ProcedimentoBuilder builder = new ProcedimentoBuilder();
		builder.procedimento = new Procedimento();
		builder.procedimento.setId(2L);
		builder.procedimento.setNome("4567");		
		return builder;		
	}
	
	
	public static ProcedimentoBuilder procedimento6789() {
		ProcedimentoBuilder builder = new ProcedimentoBuilder();
		builder.procedimento = new Procedimento();
		builder.procedimento.setId(3L);
		builder.procedimento.setNome("6789");		
		return builder;		
	}	
	
	
	public Procedimento getProcedimento() {
		return procedimento;
	}

}
