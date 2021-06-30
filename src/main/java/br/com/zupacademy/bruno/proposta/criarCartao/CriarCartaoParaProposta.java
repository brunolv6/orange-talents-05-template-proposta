package br.com.zupacademy.bruno.proposta.criarCartao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.RequestFromProposta;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.ResponseCartao;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient.CartaoAPIClient;
import br.com.zupacademy.bruno.proposta.criarProposta.Elegibilidade;
import br.com.zupacademy.bruno.proposta.criarProposta.Proposta;

@Component
@Transactional
public class CriarCartaoParaProposta {
	
	private final Logger logger = LoggerFactory.getLogger(CriarCartaoParaProposta.class);
	
	@Autowired
	private CartaoAPIClient cartaoApi;
	
	@PersistenceContext
	private EntityManager em;

	@Async
	public void solicitarCriacao(Proposta proposta)  {
		RequestFromProposta requestFromProposta = new RequestFromProposta(proposta.getDocumento(), proposta.getNome(), proposta.getId());
		
		try {
			ResponseCartao responseCartao = cartaoApi.criarCartao(requestFromProposta);
			logger.info("Cartão Criado para proposta id = {}!", proposta.getId());
			this.persistirCartaoAtualizarProposta(proposta, responseCartao);
		} catch (Exception e) {
			e.getStackTrace();
			// não fazer nada, tentaremos novamente com a função agendada verificarCartoesNaoGerados
			logger.warn("Cartão Não Criado para proposta id = {}!", proposta.getId());
		}
	}
	
	private void persistirCartaoAtualizarProposta(Proposta proposta, ResponseCartao responseCartao) {

		Cartao novoCartao = responseCartao.toModel(proposta.getUserId());
		
		proposta.setCartao(novoCartao);
		
		em.merge(proposta);
		
		logger.info("Proposta e cartão persistidos!");
	}
	
	@Scheduled(cron = "0 0/10 * * * *")
	public void verificarCartoesNaoGerados() {
		
		TypedQuery<Proposta> propostasSemCartao = em.createQuery("SELECT p FROM Proposta p WHERE p.elegibilidade=:value1 AND p.cartao IS NULL", Proposta.class);
		
		List<Proposta> listaPropostasSemCartao = propostasSemCartao.setParameter("value1", Elegibilidade.ELEGIVEL).getResultList();
		
		if(!listaPropostasSemCartao.isEmpty()) {
			listaPropostasSemCartao.forEach(propostaSemCartao -> this.solicitarCriacao(propostaSemCartao));
		} else {
			logger.info("Todas as propostas elegíveis possuem cartão associado a elas");
		}
		
	}
}
