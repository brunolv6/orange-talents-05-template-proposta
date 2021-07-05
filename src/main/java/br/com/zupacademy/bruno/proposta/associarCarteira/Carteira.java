package br.com.zupacademy.bruno.proposta.associarCarteira;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@NotNull
	@NotBlank
	private String email;

	@NotNull
	@NotBlank
	private String carteira;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Carteira() {
		super();
	}

	public Carteira(@Email @NotNull @NotBlank String email, @NotNull @NotBlank String carteira,
			@NotNull Cartao cartao) {
		super();
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

}
