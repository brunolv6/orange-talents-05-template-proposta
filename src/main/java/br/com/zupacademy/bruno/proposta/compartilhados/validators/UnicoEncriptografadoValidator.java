package br.com.zupacademy.bruno.proposta.compartilhados.validators;

import br.com.zupacademy.bruno.proposta.compartilhados.config.Criptografia;
import br.com.zupacademy.bruno.proposta.compartilhados.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UnicoEncriptografadoValidator implements ConstraintValidator<UnicoEncriptografado, String> {

    @PersistenceContext
    private EntityManager em;

    private Class<?> entidade;

    private String atributo;

    @Autowired
    private Criptografia criptografia;

    @Override
    public void initialize(UnicoEncriptografado anotacao) {
        this.entidade = anotacao.entidade();
        this.atributo = anotacao.atributo();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String valueEncriptografado = criptografia.encriptografar(value);

        Query query = em.createQuery(("select 1 from " + entidade.getName() + " where " + atributo + "= :value"));
        query.setParameter("value", valueEncriptografado);

        List<?> resultado = query.getResultList();

        if(!resultado.isEmpty()) throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "Field documento Already Exists");

        return resultado.isEmpty();
    }
}
