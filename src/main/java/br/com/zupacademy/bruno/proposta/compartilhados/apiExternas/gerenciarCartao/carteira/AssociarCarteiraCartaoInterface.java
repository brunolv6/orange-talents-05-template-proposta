package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.carteira;

import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;

public interface AssociarCarteiraCartaoInterface {

	Boolean associarCarteira(Cartao cartao, String email, String carteira);
}
