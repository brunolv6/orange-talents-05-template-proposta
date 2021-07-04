package br.com.zupacademy.bruno.proposta.avisarViagemCartao;


import br.com.zupacademy.bruno.proposta.compartilhados.errors.ApiException;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import br.com.zupacademy.bruno.proposta.criarCartao.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartoes")
public class AvisarCartaoController {

    private final Logger logger = LoggerFactory.getLogger(AvisarCartaoController.class);

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/{id}/avisos")
    @Transactional
    public ResponseEntity<?> avisar(@PathVariable(value = "id") Long idCartao, @RequestBody @Valid AvisoRequest avisoRequest, Authentication authentication, HttpServletRequest request){

        Cartao cartao = em.find(Cartao.class, idCartao);

        if(cartao == null) throw new ApiException(HttpStatus.NOT_FOUND, "Cartao não encontrada!");

        if(!cartao.isThisUserId(authentication.getName())) throw new ApiException(HttpStatus.UNAUTHORIZED, "Não autorizado!");
        
        Aviso novoAviso = avisoRequest.toModel(request.getRemoteAddr(), cartao);

        em.persist(novoAviso);

        cartao.setAviso(novoAviso);

        em.merge(cartao);
        
        logger.info("Aviso de id = {} foi confirmado no sistema interno", novoAviso.getId());
        
        return ResponseEntity.ok().build();
    }
}
