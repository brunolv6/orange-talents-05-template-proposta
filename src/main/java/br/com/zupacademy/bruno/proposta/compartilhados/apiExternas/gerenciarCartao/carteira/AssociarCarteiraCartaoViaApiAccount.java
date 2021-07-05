package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.carteira;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso.RequestCartaoAviso;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso.ResponseCartaoAviso;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.bloqueio.BloquearCartaoViaApiAccount;
import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.feignClient.CartaoAPIClient;
import br.com.zupacademy.bruno.proposta.criarCartao.Cartao;
import feign.FeignException;

@Component
public class AssociarCarteiraCartaoViaApiAccount implements AssociarCarteiraCartaoInterface{

	private final Logger logger = LoggerFactory.getLogger(AssociarCarteiraCartaoViaApiAccount.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CartaoAPIClient apiClient;
	
	@Override
	public Boolean associarCarteira(Cartao cartao, String email, String carteira) {
		RequestCartaoCarteira requestCarteira = new RequestCartaoCarteira(email, carteira);

        try {
            ResponseCartaoCarteira responseCarteira = apiClient.associarCarteiraCartao(cartao.getIdCartao(), requestCarteira);
            System.out.println(responseCarteira.getResultado());
            // response deve possuir atributo resultado com ASSOCIADA ou FALHA
            return responseCarteira.getResultado().contains("ASSOCIADA");
        } catch (FeignException e) {
            // no caso de recebermos uma exceção, se for 422 pode ser que tenha o body com o resultado
            if(e.status() == 422 && e.contentUTF8().contains("FALHA")) {
                return false;
            }

            logger.error("Pedido de associação a carteira emitiu um erro 4xx ou 5xx não esperado");

            throw e;
        }
	}

}
