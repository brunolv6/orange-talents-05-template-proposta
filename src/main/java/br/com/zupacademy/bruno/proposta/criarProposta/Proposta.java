package br.com.zupacademy.bruno.proposta.criarProposta;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.bruno.proposta.compartilhados.validators.CPFOrCNPJ;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	public Proposta(@NotEmpty @NotNull String documento, @NotEmpty @NotNull @Email String email,
			@NotEmpty @NotNull String nome, @NotEmpty @NotNull String endereco,
			@NotNull @PositiveOrZero BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

}
