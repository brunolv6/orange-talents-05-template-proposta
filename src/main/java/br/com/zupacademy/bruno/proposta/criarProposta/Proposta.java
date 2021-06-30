package br.com.zupacademy.bruno.proposta.criarProposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.bruno.proposta.compartilhados.validators.CPFOrCNPJ;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@NotNull
	@CPFOrCNPJ
	@Column(unique = true)
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

	@OneToOne(cascade = CascadeType.ALL)
	private Cartao cartao = null;

	@Enumerated(EnumType.STRING)
	private Elegibilidade elegibilidade = Elegibilidade.NAO_ELEGIVEL;

	@NotNull
	@NotEmpty
	private String userId;

	@Deprecated
	public Proposta() {
		super();
	}

	public Proposta(@NotEmpty @NotNull String documento, @NotEmpty @NotNull @Email String email,
			@NotEmpty @NotNull String nome, @NotEmpty @NotNull String endereco,
			@NotNull @PositiveOrZero BigDecimal salario, @NotEmpty @NotNull String userId) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.userId = userId;
	}

	public Long getId() {
//		if (id == null) {
//			throw new IllegalArgumentException("Id of Proposta must not be null");
//		}
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Elegibilidade getElegibilidade() {
		return elegibilidade;
	}

	public void setElegibilidade(Elegibilidade elegibilidade) {
		this.elegibilidade = elegibilidade;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public String getUserId() {
		return userId;
	}
}
