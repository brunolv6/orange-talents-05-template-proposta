package br.com.zupacademy.bruno.proposta.criarProposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController	
@RequestMapping("/api/propostas")
public class PropostaController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> gerar(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) {
		
		Proposta novaProposta = propostaRequest.toModel();
		
		em.persist(novaProposta);
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
