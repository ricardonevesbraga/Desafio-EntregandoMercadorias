package br.com.aplicacao.repository;

import java.util.List;

import br.com.aplicacao.dto.Mapa;
import br.com.aplicacao.dto.MelhorCaminho;
import br.com.aplicacao.exception.RepositoryException;

/**
 * Repository interface
 * 
 * @author Ricardo Braga
 */
public interface RESTRepository {

	MelhorCaminho getMelhorCaminho(String origem,String destino,double autonomia,double valorLitro) throws RepositoryException;

	void incluirMapa(List<Mapa> mapa) throws RepositoryException;
}
