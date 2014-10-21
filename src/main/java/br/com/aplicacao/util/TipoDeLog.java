package br.com.aplicacao.util;

/**
 * Tipo de log do sistema
 * 
 * @author Ricardo Braga
 */
public enum TipoDeLog {

	emArquivo("A"),
	noMongoDB("M");

	TipoDeLog(String value) {
		this.value = value;
	}

	private String value;

	public String value() {
		return value;
	}
}
