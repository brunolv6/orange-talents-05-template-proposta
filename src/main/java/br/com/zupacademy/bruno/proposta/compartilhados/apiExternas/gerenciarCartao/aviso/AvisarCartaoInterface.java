package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

public interface AvisarCartaoInterface {

	Boolean avisarCartao(Cartao cartao, String destino, String validoAte);
}
