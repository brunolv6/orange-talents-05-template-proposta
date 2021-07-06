package br.com.zupacademy.bruno.proposta.compartilhados.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, CONSTRUCTOR })
@Retention(RUNTIME)
@Constraint(validatedBy = { UnicoEncriptografadoValidator.class })
public @interface UnicoEncriptografado {
    String message() default "Já existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entidade();

    String atributo();
}
