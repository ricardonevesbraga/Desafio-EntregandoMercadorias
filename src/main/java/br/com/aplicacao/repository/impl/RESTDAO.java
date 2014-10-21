package br.com.aplicacao.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.com.aplicacao.dao.BaseDAO;
import br.com.aplicacao.dto.Mapa;
import br.com.aplicacao.dto.MelhorCaminho;
import br.com.aplicacao.exception.RepositoryException;
import br.com.aplicacao.repository.RESTRepository;

/**
 * Data Access Object - Acessa o Banco de dados
 * 
 * @author Ricardo Braga
 */
@Repository
public class RESTDAO extends BaseDAO implements RESTRepository {

	private static final String PKG_ENTREGA_MERCADORIAS = "pkg_entrega_mercadorias";
	private static final String PRC_RETORNA_MELHOR_CAMINHO = "prc_retorna_melhor_caminho";
	private static final String PRC_INSERE_LINHAS_MAPA = "prc_insere_linhas_mapa";
	private static final int QT_PARAMETROS_PRC_RETORNA_MELHOR_CAMINHO = 6;
	private static final int QT_PARAMETROS_PRC_INSERE_LINHAS_MAPA = 4;
	private static Logger logger = Logger.getLogger(br.com.aplicacao.repository.impl.RESTDAO.class);

	@Override
	public MelhorCaminho getMelhorCaminho(String origem,String destino,double autonomia,double valorLitro) throws RepositoryException {

		MelhorCaminho melhorCaminho = new MelhorCaminho();
		Connection connection = null;
		CallableStatement callable = null;
		PreparedStatement ps = null;
		int index = 0;

		try {
			logger.info("Buscando a connection com o Banco de Dados");

			connection = getConnection();
			callable = connection.prepareCall(geraChamadaProcedure(PKG_ENTREGA_MERCADORIAS,PRC_RETORNA_MELHOR_CAMINHO,QT_PARAMETROS_PRC_RETORNA_MELHOR_CAMINHO));

			logger.info("Setando os parâmetros");
			logger.info("Origem: " + origem);
			logger.info("Destino: " + destino);
			logger.info("Autonomia: " + autonomia);
			logger.info("Valor Litro: " + valorLitro);
			this.setPSParameter(callable,++index,origem);
			this.setPSParameter(callable,++index,destino);
			this.setPSParameter(callable,++index,autonomia);
			this.setPSParameter(callable,++index,valorLitro);
			callable.registerOutParameter(++index,OracleTypes.CURSOR);
			callable.registerOutParameter(++index,OracleTypes.VARCHAR);

			logger.info("Chamando a procedure no banco de dados");
			callable.execute();
			logger.info("Retornando resultado da procedure");
			String mensErro = this.getStringValue(callable,index);

			if (mensErro != null) { throw new Exception(mensErro); }

			ResultSet rs = this.getResultSetValue(callable,--index);

			while (rs.next()) {
				int i = 0;

				melhorCaminho.setOrigem(this.getStringValue(rs,++i));
				melhorCaminho.setDestino(this.getStringValue(rs,++i));
				melhorCaminho.setCaminho(this.getStringValue(rs,++i));
				melhorCaminho.setMenorCaminho(this.getLongValue(rs,++i));
				melhorCaminho.setAutonomia(this.getDoubleValue(rs,++i));
				melhorCaminho.setValorLitro(this.getDoubleValue(rs,++i));
				melhorCaminho.setCusto(this.getDoubleValue(rs,++i));
			}
			// Se autonomia for igual a zero é porque não achou nenhuma rota para os parametros informados
			if (melhorCaminho.getAutonomia() == 0) { throw new Exception("Nao foi encontrado nenhum caminho para os parametros informados"); }
			rs.close();
		} catch (Exception cde) {
			logger.error("Erro ao buscar informações da procedure no BD: " + cde.getMessage());
			throw new RepositoryException(cde.getMessage(),cde.getCause());
		} finally {
			this.releaseConnection(connection,callable,ps);
		}
		return melhorCaminho;
	}

	@Override
	public void incluirMapa(List<Mapa> mapa) throws RepositoryException {
		for (int i = 0 ; i < mapa.size() ; i++) {
			Connection connection = null;
			CallableStatement callable = null;
			PreparedStatement ps = null;
			int index = 0;
			try {
				logger.info("Buscando a connection com o Banco de Dados");

				connection = getConnection();
				callable = connection.prepareCall(geraChamadaProcedure(PKG_ENTREGA_MERCADORIAS,PRC_INSERE_LINHAS_MAPA,QT_PARAMETROS_PRC_INSERE_LINHAS_MAPA));

				String origem = mapa.get(i).getOrigem();
				String destino = mapa.get(i).getDestino();
				Long distancia = mapa.get(i).getDistancia();
				logger.info("Setando os parâmetros");
				logger.info("Origem: " + origem);
				logger.info("Destino: " + destino);
				logger.info("Autonomia: " + distancia);
				this.setPSParameter(callable,++index,origem);
				this.setPSParameter(callable,++index,destino);
				this.setPSParameter(callable,++index,distancia);
				callable.registerOutParameter(++index,OracleTypes.VARCHAR);

				logger.info("Chamando a procedure no banco de dados");
				callable.execute();
				logger.info("Retornando resultado da procedure");
				String mensErro = this.getStringValue(callable,index);
				if (mensErro != null) { throw new Exception(mensErro); }
			} catch (Exception cde) {
				logger.error("Erro ao buscar informações da procedure no BD: " + cde.getMessage());
				throw new RepositoryException(cde.getMessage(),cde.getCause());
			} finally {
				this.releaseConnection(connection,callable,ps);
			}
		}
	}

}
