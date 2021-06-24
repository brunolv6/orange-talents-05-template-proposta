package br.com.zupacademy.bruno.proposta.criarProposta;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta.AnalisePropostaViaApi;
import br.com.zupacademy.bruno.proposta.criarCartao.CriarCartaoParaProposta;

@RestController	
@RequestMapping("/api/propostas")
public class PropostaController {
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
	
	@Autowired
	private PropostaRepository repository;
	
	@Autowired
	private AnalisePropostaViaApi analiseProposta;
	
	@Autowired
	private CriarCartaoParaProposta criarCartaoParaProposta;
	
	@PostMapping
	public ResponseEntity<?> gerar(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) {
		
		Proposta novaProposta = propostaRequest.toModel();
		
		repository.saveAndFlush(novaProposta);
		
		logger.info("Proposta do nome = {} e id = {} criada com sucesso!", novaProposta.getNome(), novaProposta.getId());
		
		if(analiseProposta.verificarElegibilidade(novaProposta)) {
			novaProposta.setElegibilidade(Elegibilidade.ELEGIVEL);
			repository.saveAndFlush(novaProposta);
			
			criarCartaoParaProposta.solicitarCriacao(novaProposta);
			System.out.println("pós-solicitacao elegível");
			
		}
		
		logger.info("Análise de elegibilidade da proposta do nome = {} efetuada com sucesso", novaProposta.getNome());
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
