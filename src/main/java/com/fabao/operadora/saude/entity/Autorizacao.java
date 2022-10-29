package com.fabao.operadora.saude.entity;

import java.util.Date;

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

import com.fabao.operadora.saude.enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Autorizacao {
	
	@Id
	@Column(name = "ID_AUTORIZACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = false)
	private Status status;	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BENEFICIARIO", referencedColumnName = "ID_BENEFICIARIO",
    		nullable = false, updatable = false)
	private Beneficiario beneficiario;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROCEDIMENTO", referencedColumnName = "ID_PROCEDIMENTO",
    		nullable = false, updatable = false)
	private Procedimento procedimento;
	
	
	@Column(nullable = false, unique = false)
	private Date dataAutorizacao;

	
}
