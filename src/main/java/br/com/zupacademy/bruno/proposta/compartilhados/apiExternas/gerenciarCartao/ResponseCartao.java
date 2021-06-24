package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import br.com.zupacademy.bruno.proposta.criarProposta.Proposta;

public class ResponseCartao {

	@NotNull
	@NotEmpty
	private String id;

	@NotNull
	@PastOrPresent
	private LocalDateTime emitidoEm;

	@NotNull
	@NotEmpty
	private String titular;

	@NotNull
	@NotEmpty
	private String idProposta;

	@NotNull
	@PositiveOrZero
	private BigDecimal limite;

	public ResponseCartao(@NotNull @NotEmpty String id, @NotNull @PastOrPresent String emitidoEm,
			@NotNull @NotEmpty String titular, @NotNull @NotEmpty String idProposta,
			@NotNull @PositiveOrZero BigDecimal limite) {
		super();
		this.id = id;
		this.emitidoEm = LocalDateTime.parse(emitidoEm);
		this.titular = titular;
		this.idProposta = idProposta;
		this.limite = limite;
	}

	@Override
	public String toString() {
		return "ResponseCartao [id=" + id + ", emitidoEm=" + emitidoEm + ", titular=" + titular + ", idProposta="
				+ idProposta + ", limite=" + limite + "]";
	}

	public Cartao toModel() {
		return new Cartao(this.id, this.emitidoEm, this.limite);
	}

}
