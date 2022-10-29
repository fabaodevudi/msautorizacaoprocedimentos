package com.fabao.operadora.saude.enums;

public enum Sexo {
	M("MASCULINO"), F("FEMININO");

	private String label;

	private Sexo(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
