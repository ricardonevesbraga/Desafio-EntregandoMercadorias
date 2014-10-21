package br.com.aplicacao.type;

/**
 * Informacoes gerais do sistema
 * 
 * @author Ricardo Braga
 */
public enum WalmartRotas {

	nome("Walmart Rotas"),
	versao("0.0.1-SNAPSHOT"),
	build("21/10/2014"),
	encodingPadrao("UTF-8"),
	dataSource("jdbc/walmartDS"),
	nomeDoArquivoDeLog("walmart-rotas.log"),
	variavelAmbiente("tokiomarine.infra.AMBIENTE"),
	cdnVersao("3.1.1");

	WalmartRotas(String value) {
		this.value = value;
	}

	WalmartRotas(Integer number) {
		this.number = number;
	}

	private String value;
	private Integer number;

	public String value() {
		return value;
	}

	public Integer number() {
		return number;
	}
}
