package com.fabao.operadora.saude.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fabao.operadora.saude.enums.Sexo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Beneficiario {
	
	@Id
	@Column(name = "ID_BENEFICIARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	  
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = false)
	private Sexo sexo;
	  
	@Column(nullable = false, unique = false)
	private Integer idade;
	
	@OneToMany(mappedBy = "procedimento")
	private Collection<Autorizacao> autorizacoes;

}
