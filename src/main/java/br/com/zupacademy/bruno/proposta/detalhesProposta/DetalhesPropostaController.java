package br.com.zupacademy.bruno.proposta.detalhesProposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.proposta.compartilhados.errors.ApiException;
import br.com.zupacademy.bruno.proposta.criarProposta.Proposta;

@RestController
@RequestMapping("/api/propostas")
public class DetalhesPropostaController {
	
	private final Logger logger = LoggerFactory.getLogger(DetalhesPropostaController.class);

	@PersistenceContext
	private EntityManager em;
	
	@GetMapping("/{id}/detalhes")
	@Transactional
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		
		Proposta proposta = em.find(Proposta.class, id);
		
		if(proposta == null) {
			logger.warn("Proposta de id = {} não encontrada!", id);
			throw new ApiException(HttpStatus.NOT_FOUND, "Proposta não encontrada!");
		}
		
		return ResponseEntity.ok(new PropostaDetalhesResponse(proposta));
	}
}
