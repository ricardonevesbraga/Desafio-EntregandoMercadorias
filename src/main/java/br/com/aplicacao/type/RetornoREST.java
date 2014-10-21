package br.com.aplicacao.type;

/**
 * Mensagens de retorno do REST para os serviços
 * 
 * @author Ricardo Braga
 */
public enum RetornoREST {

	sucesso(0,"sucesso"),
	registroNaoEncontrado(-1,"registro nao encontrado"),
	erroInesperado(-99,"erro inesperado");

	RetornoREST(int codigo,String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	private int codigo;
	private String mensagem;

	public int codigo() {
		return codigo;
	}

	public String mensagem() {
		return mensagem;
	}

}
