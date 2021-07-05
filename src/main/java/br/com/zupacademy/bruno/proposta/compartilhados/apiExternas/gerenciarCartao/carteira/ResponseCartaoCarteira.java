package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.gerenciarCartao.carteira;

public class ResponseCartaoCarteira {

	private String resultado;

	private String id;

	public ResponseCartaoCarteira(String resultado, String id) {
		super();
		this.resultado = resultado;
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
