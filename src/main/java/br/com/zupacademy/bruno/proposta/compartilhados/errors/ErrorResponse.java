package br.com.zupacademy.bruno.proposta.compartilhados.errors;

public class ErrorResponse {
	private String field;

	private String error;

	public ErrorResponse(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}

}
