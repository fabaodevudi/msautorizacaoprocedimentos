package com.fabao.operadora.saude.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fabao.operadora.saude.enums.Sexo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Regra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REGRA")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROCEDIMENTO", referencedColumnName = "ID_PROCEDIMENTO",
    		nullable = false, updatable = false)
	private Procedimento procedimento;
	  
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = false)
	private Sexo sexo;
	  
	@Column(nullable = false, unique = false)
	private Integer idade;	  

}
