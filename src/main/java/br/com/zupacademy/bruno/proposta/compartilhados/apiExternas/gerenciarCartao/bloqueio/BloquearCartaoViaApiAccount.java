package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient.CartaoAPIClient;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class BloquearCartaoViaApiAccount implements BloquearCartaoInterface{

    private final Logger logger = LoggerFactory.getLogger(BloquearCartaoViaApiAccount.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CartaoAPIClient apiClient;

    @Override
    public Boolean bloquearCartao(Cartao cartao, String ip) {

        RequestCartaoBloqueio requestBloqueio = new RequestCartaoBloqueio(ip);

        try {
            ResponseCartaoBloqueio responseBloqueio = apiClient.bloquearCartao(cartao.getIdCartao(), requestBloqueio);
            // response deve possuir atributo resultado com BLOQUEADO ou FALHA
            return responseBloqueio.getResultado().contains("BLOQUEADO");
        } catch (FeignException e) {
            // no caso de recebermos uma exceção, se for 422 pode ser que tenha o body com o resultado
            if(e.status() == 422 && e.contentUTF8().contains("FALHA")) {
                return false;
            }

            logger.error("Pedido de bloqueio emitiu um erro 4xx ou 5xx não esperado");

            throw e;
        }
    }
}
