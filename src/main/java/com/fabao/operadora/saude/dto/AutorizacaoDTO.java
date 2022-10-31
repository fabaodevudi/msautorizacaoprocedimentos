package com.fabao.operadora.saude.dto;

import com.fabao.operadora.saude.entity.Autorizacao;
import com.fabao.operadora.saude.utils.DataUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorizacaoDTO {
		
	private String nomeBeneficiario;
	
	private String cpf;
	
	private String nomeProcedimento;
	
	private String dataAutorizacao;
	
	
	public static AutorizacaoDTO fromEntity(Autorizacao entity) {
		AutorizacaoDTO dto = new AutorizacaoDTO();
		dto.setCpf(entity.getBeneficiario().getCpf());
		dto.setNomeBeneficiario(entity.getBeneficiario().getNome());
		dto.setNomeProcedimento(entity.getProcedimento().getNome());
		dto.setDataAutorizacao(DataUtil.converterDateToString(entity.getDataAutorizacao(), "dd/MM/yyyy"));
		return dto;
	}	

}
