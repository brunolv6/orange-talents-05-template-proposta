package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RequestCartaoAviso {

	@NotNull
	@NotBlank
    private String destino;
	
	@NotNull
	@NotBlank
    private String validoAte;

	@JsonCreator
	public RequestCartaoAviso(@NotNull @NotBlank String destino, @NotNull @NotBlank String validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getValidoAte() {
		return validoAte;
	}

	public void setValidoAte(String validoAte) {
		this.validoAte = validoAte;
	}
	
	
	
}
