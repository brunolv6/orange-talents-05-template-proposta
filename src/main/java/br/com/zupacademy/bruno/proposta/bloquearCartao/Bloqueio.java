package br.com.zupacademy.bruno.proposta.bloquearCartao;

import br.com.zupacademy.bruno.proposta.compartilhados.validators.IsBase64;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    @NotNull
    @NotBlank
    private String ip;

    @PastOrPresent
    private LocalDateTime criadoEm = LocalDateTime.now();

    @NotNull
    private Boolean bloqueioEfetuado = false;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(Cartao cartao, String ip) {
        this.cartao = cartao;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getIp() {
        return ip;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setBloqueioEfetuado(Boolean bloqueioEfetuado) {
        this.bloqueioEfetuado = bloqueioEfetuado;
    }
}
