package br.com.aplicacao.dto;

/**
 * Data Transfer Object Mapa
 * 
 * @author Ricardo Braga
 */
public class Mapa {

	private String origem;
	private String destino;
	private Long distancia;

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Long getDistancia() {
		return distancia;
	}

	public void setDistancia(Long distancia) {
		this.distancia = distancia;
	}

}
