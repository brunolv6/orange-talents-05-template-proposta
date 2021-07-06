package br.com.zupacademy.bruno.proposta.criarProposta;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta.AnalisePropostaViaApi;
import br.com.zupacademy.bruno.proposta.criarCartao.CriarCartaoParaProposta;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController	
@RequestMapping("/api/propostas")
public class PropostaController {
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	private PropostaRepository repository;

	private AnalisePropostaViaApi analiseProposta;

	private CriarCartaoParaProposta criarCartaoParaProposta;

	private final MeterRegistry meterRegistry;
	
	private final Tracer tracer;
	

	public PropostaController(PropostaRepository repository, AnalisePropostaViaApi analiseProposta, CriarCartaoParaProposta criarCartaoParaProposta, MeterRegistry meterRegistry, Tracer tracer) {
		this.repository = repository;
		this.analiseProposta = analiseProposta;
		this.criarCartaoParaProposta = criarCartaoParaProposta;
		this.meterRegistry = meterRegistry;
		this.tracer = tracer;
	}

	@PostMapping
	public ResponseEntity<?> gerar(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder, Authentication authentication) {
		
		// Testando OpenTracing
        Span activeSpan = tracer.activeSpan();
        
        activeSpan.setTag("usuario.nome", propostaRequest.getNome());
        
        activeSpan.setBaggageItem("usuario.email", propostaRequest.getEmail());

        activeSpan.log("Iniciando requisição de nova Proposta!");
		
		Proposta novaProposta = propostaRequest.toModel(authentication.getName());
		
		repository.saveAndFlush(novaProposta);
		
		logger.info("Proposta do nome = {} e id = {} criada com sucesso!", novaProposta.getNome(), novaProposta.getId());

		Counter propostasElegiveis = meterRegistry.counter("proposta_elegiveis");
		Counter propostasNaoElegiveis = meterRegistry.counter("proposta_nao_elegiveis");

		if(analiseProposta.verificarElegibilidade(novaProposta)) {
			propostasElegiveis.increment();
			novaProposta.setElegibilidade(Elegibilidade.ELEGIVEL);
			repository.saveAndFlush(novaProposta);
			
			criarCartaoParaProposta.solicitarCriacao(novaProposta);
			System.out.println("pós-solicitacao elegível");
			
		} else {
			propostasNaoElegiveis.increment();
		}

		logger.info("Análise de elegibilidade da proposta do nome = {} efetuada com sucesso", novaProposta.getNome());
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
