package br.com.zupacademy.bruno.proposta.compartilhados.errors;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	private final String reason;

	public ApiException(HttpStatus httpStatus, String reason) {
		super(reason);
		this.httpStatus = httpStatus;
		this.reason = reason;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getReason() {
		return reason;
	}

}
