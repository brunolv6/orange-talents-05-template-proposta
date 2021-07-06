package br.com.zupacademy.bruno.proposta.compartilhados.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
public class Criptografia {

    @Value("${proposta.segredo}")
    private String segredo;

    @Value("${proposta.salt}")
    private String salt;

    public String encriptografar(String texto) {
        return Encryptors.queryableText(segredo, salt).encrypt(texto);
    }

    public String decriptografar(String texto) {
        return Encryptors.queryableText(segredo, salt).decrypt(texto);
    }
}
