package br.com.zupacademy.bruno.proposta.compartilhados.errors;

import java.util.Collection;

public class ErrorResponse {

	private Collection<String> mensagens;

	public ErrorResponse(Collection<String> mensagens) {
		this.mensagens = mensagens;
	}

	public Collection<String> getMensagens() {
		return mensagens;
	}

}
