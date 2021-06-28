package br.com.zupacademy.bruno.proposta.adicionarBiometria;

import br.com.zupacademy.bruno.proposta.compartilhados.validators.IsBase64;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @IsBase64
    private String dadosBiometria;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String dadosBiometria, Cartao cartao) {
        this.dadosBiometria = dadosBiometria;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDadosBiometria() {
        return dadosBiometria;
    }

    public Cartao getCartao() {
        return cartao;
    }

}
