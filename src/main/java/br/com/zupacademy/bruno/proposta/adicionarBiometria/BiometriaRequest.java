package br.com.zupacademy.bruno.proposta.adicionarBiometria;

import br.com.zupacademy.bruno.proposta.compartilhados.validators.IsBase64;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BiometriaRequest {

    @NotNull
    @NotEmpty
    @IsBase64
    private String dadosBiometria;

    public void setDadosBiometria(String dadosBiometria) {
        this.dadosBiometria = dadosBiometria;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(dadosBiometria, cartao);
    }
}
