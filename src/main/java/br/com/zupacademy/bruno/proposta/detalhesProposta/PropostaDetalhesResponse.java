package br.com.zupacademy.bruno.proposta.detalhesProposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.bruno.proposta.compartilhados.validators.CPFOrCNPJ;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import br.com.zupacademy.bruno.proposta.criarProposta.Proposta;

public class PropostaDetalhesResponse {

	@NotEmpty
	@NotNull
	@CPFOrCNPJ
	private String documento;

	@NotEmpty
	@NotNull
	@Email
	private String email;

	@NotEmpty
	@NotNull
	private String nome;

	@NotEmpty
	@NotNull
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	private CartaoResponse cartaoResponse;

	private String elegibilidade;

	public PropostaDetalhesResponse(Proposta proposta) {
		super();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.cartaoResponse = this.toModelCartaoResponse(proposta.getCartao());
		this.elegibilidade = proposta.getElegibilidade().name();
	}
	
	private CartaoResponse toModelCartaoResponse(Cartao cartao) {
		if(cartao == null) {
			return null;
		}
		return new CartaoResponse(cartao);
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public CartaoResponse getCartaoResponse() {
		return cartaoResponse;
	}

	public String getElegibilidade() {
		return elegibilidade;
	}

}
