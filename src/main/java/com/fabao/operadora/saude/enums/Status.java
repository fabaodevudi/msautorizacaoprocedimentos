package com.fabao.operadora.saude.enums;

public enum Status {
	A("APROVADO"), R("APROVADO");

	private String label;

	private Status(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
