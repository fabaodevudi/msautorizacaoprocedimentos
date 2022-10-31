package com.fabao.operadora.saude.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AutorizacaoException extends RuntimeException {
	private static final long serialVersionUID = 3837982804180390821L;

	public AutorizacaoException(String message) {
		super(message);
	}

}
