package com.fabao.operadora.saude.dto;

import java.util.Date;

import com.fabao.operadora.saude.entity.Autorizacao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorizacaoDTO {
		
	private String nomeBeneficiario;
	
	private String cpf;
	
	private String nomeProcedimento;
	
	private Date dataAutorizacao;
	
	
	public static AutorizacaoDTO fromEntity(Autorizacao entity) {
		AutorizacaoDTO dto = new AutorizacaoDTO();
		dto.setCpf(entity.getBeneficiario().getCpf());
		dto.setNomeBeneficiario(entity.getBeneficiario().getNome());
		dto.setNomeProcedimento(entity.getProcedimento().getNome());
		dto.setDataAutorizacao(entity.getDataAutorizacao());
		return dto;
	}	

}
