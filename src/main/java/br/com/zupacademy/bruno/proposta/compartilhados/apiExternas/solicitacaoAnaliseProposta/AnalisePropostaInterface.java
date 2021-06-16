package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta;

import br.com.zupacademy.bruno.proposta.criarProposta.Proposta;

public interface AnalisePropostaInterface {
	
	Boolean verificarElegibilidade(Proposta proposta);
	
}
