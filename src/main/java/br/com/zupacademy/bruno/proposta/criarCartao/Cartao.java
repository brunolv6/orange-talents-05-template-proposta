package br.com.zupacademy.bruno.proposta.criarCartao;

import br.com.zupacademy.bruno.proposta.adicionarBiometria.Biometria;
import br.com.zupacademy.bruno.proposta.bloquearCartao.Bloqueio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String idCartao;

	@NotNull
	private LocalDateTime emitidoEm;

	@NotNull
	@PositiveOrZero
	private BigDecimal limite;

	@NotNull
	@NotEmpty
	private String userId;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Biometria> biometrias = new ArrayList<>();

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Bloqueio> bloqueios = new ArrayList<>();

	@Deprecated
	public Cartao() {
		super();
	}

	public Cartao(@NotNull @NotEmpty String idCartao, @NotNull LocalDateTime emitidoEm,
			@NotNull @PositiveOrZero BigDecimal limite, @NotEmpty @NotNull String userId) {
		super();
		this.idCartao = idCartao;
		this.emitidoEm = emitidoEm;
		this.limite = limite;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getIdCartao() {
		return idCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setBiometrias(Biometria biometria) {
		this.biometrias.add(biometria);
	}

	public void setBloqueio(Bloqueio bloqueio) {
		this.bloqueios.add(bloqueio);
	}

	public Boolean isThisUserId(String possivelUserId){
		return userId.equals(possivelUserId);
	}

	public String getUserId() {
		return userId;
	}
}
