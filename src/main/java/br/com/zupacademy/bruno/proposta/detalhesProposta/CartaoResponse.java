package br.com.zupacademy.bruno.proposta.detalhesProposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

public class CartaoResponse {

	private String id;

	@NotNull
	@PastOrPresent
	private LocalDateTime emitidoEm;

	@NotNull
	@PositiveOrZero
	private BigDecimal limite;

	public CartaoResponse(Cartao cartao) {
		super();
		this.id = cartao.getIdCartao();
		this.emitidoEm = cartao.getEmitidoEm();
		this.limite = cartao.getLimite();
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
