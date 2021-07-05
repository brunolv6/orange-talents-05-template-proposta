package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.RequestFromProposta;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.ResponseCartao;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso.RequestCartaoAviso;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso.ResponseCartaoAviso;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio.RequestCartaoBloqueio;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio.ResponseCartaoBloqueio;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.carteira.RequestCartaoCarteira;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.carteira.ResponseCartaoCarteira;

@FeignClient(url = "${spring.contas.url}", name = "cartaoApi")
public interface CartaoAPIClient {

	@PostMapping("/api/cartoes")
	ResponseCartao criarCartao(@RequestBody RequestFromProposta requestFromProposta);
	
	@GetMapping("/api/cartoes")
	ResponseCartao buscarCartao(@RequestParam String idProposta);

	@PostMapping(path = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
	ResponseCartaoBloqueio bloquearCartao(@PathVariable String id, @RequestBody RequestCartaoBloqueio requestCartaoBloqueio);
	
	@PostMapping(path = "/api/cartoes/{id}/avisos", consumes = "application/json")
	ResponseCartaoAviso avisarCartao(@PathVariable String id, @RequestBody RequestCartaoAviso requestCartaoAviso);
	
	@PostMapping(path = "/api/cartoes/{id}/carteiras", consumes = "application/json")
	ResponseCartaoCarteira associarCarteiraCartao(@PathVariable String id, @RequestBody RequestCartaoCarteira requestCartaoCarteira);
}
