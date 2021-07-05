package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RequestCartaoCarteira {

	@Email
	@NotNull
	@NotBlank
	private String email;

	@NotNull
	@NotBlank
	private String carteira;

	@JsonCreator
	public RequestCartaoCarteira(@Email @NotNull @NotBlank String email, @NotNull @NotBlank String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

}
