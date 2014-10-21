package br.com.aplicacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aplicacao.dto.Mapa;
import br.com.aplicacao.dto.MelhorCaminho;
import br.com.aplicacao.exception.RepositoryException;
import br.com.aplicacao.exception.ServiceException;
import br.com.aplicacao.repository.RESTRepository;

/**
 * Serviços disponíveis
 * 
 * @author Ricardo Braga
 */
@Service("restService")
public class RESTService {

	@Autowired
	private RESTRepository repository;

	public MelhorCaminho getMelhorCaminho(String origem,String destino,double autonomia,double valorLitro) throws ServiceException {

		MelhorCaminho melhorCaminho = null;
		try {
			melhorCaminho = repository.getMelhorCaminho(origem,destino,autonomia,valorLitro);
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(),re.getCause());
		}
		return melhorCaminho;
	}

	public void incluirMapa(List<Mapa> mapa) throws ServiceException {

		try {
			repository.incluirMapa(mapa);
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(),re.getCause());
		}
	}
}
