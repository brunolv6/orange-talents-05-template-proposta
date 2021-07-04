package br.com.zupacademy.bruno.proposta.avisarViagemCartao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

@Entity
public class Aviso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime createdAt = LocalDateTime.now();

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate terminoViagem;

	@NotNull
	@NotBlank
	private String ip;

	@NotNull
	@NotBlank
	private String destino;

	@ManyToOne
	private Cartao cartao;

	@JsonCreator
	public Aviso(@NotNull @Future LocalDate terminoViagem, @NotNull @NotBlank String ip,
			@NotNull @NotBlank String destino, Cartao cartao) {
		super();
		this.terminoViagem = terminoViagem;
		this.ip = ip;
		this.destino = destino;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Aviso [id=" + id + ", createdAt=" + createdAt + ", terminoViagem=" + terminoViagem + ", ip=" + ip
				+ ", destino=" + destino + "]";
	}

}
