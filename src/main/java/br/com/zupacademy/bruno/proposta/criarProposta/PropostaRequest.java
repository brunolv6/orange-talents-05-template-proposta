package br.com.zupacademy.bruno.proposta.criarProposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.bruno.proposta.compartilhados.config.Criptografia;
import br.com.zupacademy.bruno.proposta.compartilhados.validators.CPFOrCNPJ;
import br.com.zupacademy.bruno.proposta.compartilhados.validators.Unico;
import br.com.zupacademy.bruno.proposta.compartilhados.validators.UnicoEncriptografado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PropostaRequest {

	@NotEmpty
	@NotNull
	@CPFOrCNPJ
	@UnicoEncriptografado(entidade = Proposta.class, atributo = "documento")
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

	public PropostaRequest(@NotEmpty @NotNull String documento, @NotEmpty @NotNull @Email String email,
			@NotEmpty @NotNull String nome, @NotEmpty @NotNull String endereco,
			@NotNull @PositiveOrZero BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "PropostaRequest [documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}

	public Proposta toModel(String userId, Criptografia criptografia) {
		return new Proposta(criptografia.encriptografar(documento), email, nome, endereco, salario, userId);
	}

}
