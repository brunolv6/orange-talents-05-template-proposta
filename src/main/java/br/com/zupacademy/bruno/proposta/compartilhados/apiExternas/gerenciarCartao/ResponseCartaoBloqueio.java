package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao;

public class ResponseCartaoBloqueio {

    private String resultado;

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "ResponseCartaoBloqueio{" +
                "resultado='" + resultado + '\'' +
                '}';
    }
}
