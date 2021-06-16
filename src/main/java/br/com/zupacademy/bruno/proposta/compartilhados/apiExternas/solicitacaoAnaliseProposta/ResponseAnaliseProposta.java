package br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta;

public class ResponseAnaliseProposta {

	private String documento;

	private String nome;

	private String resultadoSolicitacao;

	private String idProposta;

	public ResponseAnaliseProposta(String documento, String nome, String resultadoSolicitacao, String id) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		System.out.println(id);
		this.idProposta = id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}

	@Override
	public String toString() {
		return "ResponseAnaliseProposta [documento=" + documento + ", nome=" + nome + ", resultadoSolicitacao="
				+ resultadoSolicitacao + ", idProposta=" + idProposta + "]";
	}

}
