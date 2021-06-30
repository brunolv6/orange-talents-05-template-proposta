package br.com.zupacademy.bruno.proposta.bloquearCartao;

import br.com.zupacademy.bruno.proposta.adicionarBiometria.AdicionarBiometriaController;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.ResponseCartaoBloqueio;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio.BloquearCartaoViaApiAccount;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient.CartaoAPIClient;
import br.com.zupacademy.bruno.proposta.compartilhados.errors.ApiException;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import br.com.zupacademy.bruno.proposta.criarCartao.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/api/cartoes")
public class BloquearCartaoController {

    private final Logger logger = LoggerFactory.getLogger(BloquearCartaoController.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BloquearCartaoViaApiAccount bloquearCartaoApi;

    @PostMapping("{id}/bloqueios")
    @Transactional
    public ResponseEntity<?> bloquear(@PathVariable(value = "id") Long idCartao, Authentication authentication, HttpServletRequest request, UriComponentsBuilder uriBuilder){

        Cartao cartao = em.find(Cartao.class, idCartao);

        if(cartao == null) throw new ApiException(HttpStatus.NOT_FOUND, "Cartao não encontrada!");

        if(!cartao.isThisUserId(authentication.getName())) throw new ApiException(HttpStatus.UNAUTHORIZED, "Não autorizado!");

        Bloqueio novoBloqueio = persistirBloqueioECartao(cartao, request.getRemoteAddr());

        if(!bloquearCartaoApi.bloquearCartao(cartao, request.getRemoteAddr())) throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartao já está bloqueado!");

        logger.info("Cartão de id = {} foi bloqueado no servidor externo account", cartao.getId());

        novoBloqueio.setBloqueioEfetuado(true);

        em.merge(novoBloqueio);

        cartao.setStatus(Status.BLOQUEADO);

        em.merge(cartao);

        logger.info("Bloqueio de id = {} foi confirmado no sistema interno", novoBloqueio.getId());

        return ResponseEntity.ok().build();
    }

    @Transactional
    private Bloqueio persistirBloqueioECartao(Cartao cartao, String ip){
        Bloqueio novoBloqueio = new Bloqueio(cartao, ip);

        cartao.setBloqueio(novoBloqueio);

        em.persist(novoBloqueio);

        em.merge(cartao);

        logger.info("Bloqueio {} persistida junto a cartão {}", novoBloqueio.getId(), cartao.getId());

        return novoBloqueio;
    }

}
