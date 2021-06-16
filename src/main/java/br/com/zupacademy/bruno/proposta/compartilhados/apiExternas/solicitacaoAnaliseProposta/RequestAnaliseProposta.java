package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta;

public class RequestAnaliseProposta {

	private String documento;

	private String nome;

	private String idProposta;

	public RequestAnaliseProposta(String documento, String nome, Long id) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.idProposta = id.toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
