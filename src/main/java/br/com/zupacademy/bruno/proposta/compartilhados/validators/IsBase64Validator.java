package br.com.zupacademy.bruno.proposta.compartilhados.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            byte[] data = Base64.getDecoder().decode(value.getBytes());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
