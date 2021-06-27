package br.com.zupacademy.bruno.proposta.criarCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Cartao {

	@Id
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String id;

	@NotNull
	private LocalDateTime emitidoEm;

	@NotNull
	@PositiveOrZero
	private BigDecimal limite;

	@Deprecated
	public Cartao() {
		super();
	}

	public Cartao(@NotNull @NotEmpty String id, @NotNull LocalDateTime emitidoEm,
			@NotNull @PositiveOrZero BigDecimal limite) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.limite = limite;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public BigDecimal getLimite() {
		return limite;
	}

}
