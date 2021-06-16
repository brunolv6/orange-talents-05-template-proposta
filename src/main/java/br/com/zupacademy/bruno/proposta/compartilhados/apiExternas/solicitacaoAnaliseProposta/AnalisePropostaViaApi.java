package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta.feignClient.AnalisePropostaApiClient;
import br.com.zupacademy.bruno.proposta.criarProposta.Proposta;
import feign.FeignException;

@Component
public class AnalisePropostaViaApi implements AnalisePropostaInterface {

	private final Logger logger = LoggerFactory.getLogger(AnalisePropostaViaApi.class);
	
	@Autowired
	private AnalisePropostaApiClient apiClient;

	@Override
	public Boolean verificarElegibilidade(Proposta proposta) {
		RequestAnaliseProposta requestAnaliseProposta = new RequestAnaliseProposta(proposta.getDocumento(), proposta.getNome(), proposta.getId());	
		
		logger.info("Indo analisar elegibilidade da proposta do nome = {}", proposta.getNome());		
		
		try {
			ResponseAnaliseProposta responseAnaliseProposta = apiClient.solicitar(requestAnaliseProposta);
			return responseAnaliseProposta.getResultadoSolicitacao().contains("SEM_RESTRICAO");
		} catch (FeignException e) {
			if(e.status() == 422 && e.contentUTF8().contains("COM_RESTRICAO")) {
				return false;
			}
			
			logger.error("Análise de elegibilidade da proposta do nome = {} emitiu um erro 4xx ou 5xx não esperado", proposta.getNome());
			
			throw e;
		}
	}

}
