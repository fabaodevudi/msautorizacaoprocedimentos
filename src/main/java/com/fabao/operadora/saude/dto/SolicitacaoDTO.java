package com.fabao.operadora.saude.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SolicitacaoDTO {
	
	private String cpf;
	private String nomeProcedimento;
}
