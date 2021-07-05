package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio.BloquearCartaoViaApiAccount;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient.CartaoAPIClient;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import feign.FeignException;

@Component
public class AvisarCartaoViaApiAccount implements AvisarCartaoInterface {

	private final Logger logger = LoggerFactory.getLogger(AvisarCartaoViaApiAccount.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CartaoAPIClient apiClient;
    
	@Override
	public Boolean avisarCartao(Cartao cartao, String destino, String validoAte) {
		RequestCartaoAviso requestAviso = new RequestCartaoAviso(destino, validoAte);

        try {
            ResponseCartaoAviso responseAviso = apiClient.avisarCartao(cartao.getIdCartao(), requestAviso);
            System.out.println(responseAviso.getResultado());
            // response deve possuir atributo resultado com CRIADO ou FALHA
            return responseAviso.getResultado().contains("CRIADO");
        } catch (FeignException e) {
            // no caso de recebermos uma exceção, se for 422 pode ser que tenha o body com o resultado
            if(e.status() == 422) {
                return false;
            }

            logger.error("Pedido de aviso de viagem emitiu um erro 4xx ou 5xx não esperado");

            throw e;
        }
	}

}
