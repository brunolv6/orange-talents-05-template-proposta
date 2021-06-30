package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

public interface BloquearCartaoInterface {

    Boolean bloquearCartao(Cartao cartao, String ip);
}
