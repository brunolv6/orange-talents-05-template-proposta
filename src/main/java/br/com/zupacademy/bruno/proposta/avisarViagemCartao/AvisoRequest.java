package br.com.zupacademy.bruno.proposta.avisarViagemCartao;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

public class AvisoRequest {

    @NotNull
    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate terminoViagem;

    @JsonCreator
    public AvisoRequest(String destino, LocalDate terminoViagem) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
    }

	@Override
	public String toString() {
		return "AvisoRequest [destino=" + destino + ", terminoViagem=" + terminoViagem + "]";
	}

	public Aviso toModel(String ip, Cartao cartao) {
		return new Aviso(this.terminoViagem, ip, this.destino, cartao);
	}
    
    
}
