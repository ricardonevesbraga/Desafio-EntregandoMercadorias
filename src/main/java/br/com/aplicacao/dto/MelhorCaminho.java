package br.com.aplicacao.dto;

/**
 * Data Transfer Object MelhorCaminho
 * 
 * @author Ricardo Braga
 */
public class MelhorCaminho {

	private String origem;
	private String destino;
	private String caminho;
	private Long menorCaminho;
	private double autonomia;
	private double valorLitro;
	private double custo;

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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Long getMenorCaminho() {
		return menorCaminho;
	}

	public void setMenorCaminho(Long menorCaminho) {
		this.menorCaminho = menorCaminho;
	}

	public double getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(double autonomia) {
		this.autonomia = autonomia;
	}

	public double getValorLitro() {
		return valorLitro;
	}

	public void setValorLitro(double valorLitro) {
		this.valorLitro = valorLitro;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

}
