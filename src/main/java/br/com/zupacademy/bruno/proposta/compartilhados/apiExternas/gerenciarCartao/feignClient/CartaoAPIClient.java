package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient;

import br.com.zupacademy.bruno.proposta.bloquearCartao.RequestCartaoBloqueio;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.ResponseCartaoBloqueio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.RequestFromProposta;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.ResponseCartao;

@FeignClient(url = "${spring.contas.url}", name = "cartaoApi")
public interface CartaoAPIClient {

	@PostMapping("/api/cartoes")
	ResponseCartao criarCartao(@RequestBody RequestFromProposta requestFromProposta);
	
	@GetMapping("/api/cartoes")
	ResponseCartao buscarCartao(@RequestParam String idProposta);

	@PostMapping(path = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
	ResponseCartaoBloqueio bloquearCartao(@PathVariable String id, @RequestBody RequestCartaoBloqueio requestCartaoBloqueio);
}
