package br.com.zupacademy.bruno.proposta.adicionarBiometria;

import br.com.zupacademy.bruno.proposta.compartilhados.errors.ApiException;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import br.com.zupacademy.bruno.proposta.detalhesProposta.DetalhesPropostaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Base64;

@RestController
@RequestMapping("/api/cartoes")
public class AdicionarBiometriaController {

    private final Logger logger = LoggerFactory.getLogger(AdicionarBiometriaController.class);

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/{id}/biometrias")
    @Transactional
    public ResponseEntity<?> criarBiometria(@PathVariable(value = "id") Long idCartao, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder uriBuilder) {

        Cartao cartao = em.find(Cartao.class, idCartao);

        if(cartao == null) {
            logger.warn("Cartao de id = {} não encontrada!", idCartao);
            throw new ApiException(HttpStatus.NOT_FOUND, "Cartao não encontrada!");
        }

        Biometria novaBiometria = biometriaRequest.toModel(cartao);

        cartao.setBiometrias(novaBiometria);

        em.persist(novaBiometria);

        em.merge(cartao);

        logger.info("Biometria {} persistida junto a cartão {}", novaBiometria.getId(), cartao.getId());

        URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(novaBiometria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
