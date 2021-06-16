package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta.RequestAnaliseProposta;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta.ResponseAnaliseProposta;

@FeignClient( url = "http://localhost:9999", name = "analiseProposta")
public interface AnalisePropostaApiClient {
	
	@PostMapping("/api/solicitacao")
	ResponseAnaliseProposta solicitar(@RequestBody RequestAnaliseProposta requestAnaliseProposta);
	
}
 