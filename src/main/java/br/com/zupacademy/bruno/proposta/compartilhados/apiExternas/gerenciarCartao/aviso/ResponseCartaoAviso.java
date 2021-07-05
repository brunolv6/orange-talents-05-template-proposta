package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.aviso;

public class ResponseCartaoAviso {

	private String resultado;

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "ResponseCartaoAviso{" +
                "resultado='" + resultado + '\'' +
                '}';
    }

}
