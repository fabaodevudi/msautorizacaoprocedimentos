package com.fabao.operadora.saude.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Procedimento {
	
	@Id
	@Column(name = "ID_PROCEDIMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = false)
	private String nome;	
	
	@OneToMany(mappedBy = "procedimento")
	private Collection<Regra> regras;

}
