package br.com.zupacademy.bruno.proposta.compartilhados.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, CONSTRUCTOR})
@Retention(RUNTIME)
@Constraint(validatedBy = {IsBase64Validator.class})
public @interface IsBase64 {
    String message() default "String is not Base64";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
