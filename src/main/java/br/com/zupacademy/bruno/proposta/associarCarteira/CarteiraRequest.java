package br.com.zupacademy.bruno.proposta.associarCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

public class CarteiraRequest {
	
	@Email
	@NotNull
	@NotBlank
	private String email;

	@NotNull
	@NotBlank
	private String carteira;

	@JsonCreator
	public CarteiraRequest(@Email @NotNull @NotBlank String email, @NotNull @NotBlank String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

	public Carteira toModel(Cartao cartao) {
		return new Carteira(this.email, this.carteira, cartao);
	}
	
	
}
