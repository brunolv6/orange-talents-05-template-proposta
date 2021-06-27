package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.RequestFromProposta;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.ResponseCartao;

@FeignClient(url = "${spring.contas.url}", name = "cartaoApi")
public interface CartaoAPIClient {

	@PostMapping("/api/cartoes")
	ResponseCartao criarCartao(@RequestBody RequestFromProposta requestFromProposta);
	
	@GetMapping("/api/cartoes")
	ResponseCartao buscarCartao(@RequestParam String idProposta);
}
