package br.com.zupacademy.bruno.proposta.associarCarteira;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.bruno.proposta.avisarViagemCartao.Aviso;
import br.com.zupacademy.bruno.proposta.avisarViagemCartao.AvisoRequest;
import br.com.zupacademy.bruno.proposta.bloquearCartao.BloquearCartaoController;
import br.com.zupacademy.bruno.proposta.bloquearCartao.Bloqueio;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio.BloquearCartaoViaApiAccount;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.carteira.AssociarCarteiraCartaoViaApiAccount;
import br.com.zupacademy.bruno.proposta.compartilhados.errors.ApiException;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import br.com.zupacademy.bruno.proposta.criarCartao.Status;


@RestController
@RequestMapping("/api/cartoes")
public class AssociarCarteiraController {

	private final Logger logger = LoggerFactory.getLogger(AssociarCarteiraController.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AssociarCarteiraCartaoViaApiAccount associarCartaoApi;

    @PostMapping("/{id}/carteiras")
    @Transactional
    public ResponseEntity<?> associar(@PathVariable(value = "id") Long idCartao, @RequestBody @Valid CarteiraRequest carteiraRequest, Authentication authentication, HttpServletRequest request){

        Cartao cartao = em.find(Cartao.class, idCartao);

        if(cartao == null) throw new ApiException(HttpStatus.NOT_FOUND, "Cartao não encontrada!");

        if(!cartao.isThisUserId(authentication.getName())) throw new ApiException(HttpStatus.UNAUTHORIZED, "Não autorizado!");
        
        // verificar se cartao já está associado a este tipo de carteira
        if(!associarCartaoApi.associarCarteira(cartao, carteiraRequest.getEmail(), carteiraRequest.getCarteira())) throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira já associada ao cartão");
        
        logger.info("Cartão de id = {} foi associado a nova carteira no servidor externo account", cartao.getId());
        
        Carteira novaCarteira = carteiraRequest.toModel(cartao);
        
        persistirAvisoECartao(cartao, novaCarteira);

        logger.info("Carteira de id = {} foi confirmado no sistema interno", novaCarteira.getId());
        
        return ResponseEntity.ok().build();
    }
    
    @Transactional
    private void persistirAvisoECartao(Cartao cartao, Carteira novaCarteira){

        cartao.setCarteiras(novaCarteira);

        em.persist(novaCarteira);

        em.merge(cartao);

        logger.info("Carteira {} persistido junto a cartão {}", novaCarteira.getId(), cartao.getId());
        
    }
}
