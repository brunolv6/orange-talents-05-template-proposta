package br.com.zupacademy.bruno.proposta.bloquearCartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;

public class RequestCartaoBloqueio {

    @NotNull
    private String sistemaResponsavel;

    @JsonCreator
    public RequestCartaoBloqueio(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
