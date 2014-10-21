package br.com.aplicacao.util;

import org.apache.log4j.Logger;

import br.com.aplicacao.type.WalmartRotas;

/**
 * Rotina para geração de LOG
 * 
 * @author Ricardo Braga
 */
public class LogUtil {

	private static final String NOME_DO_LOG = WalmartRotas.nomeDoArquivoDeLog.value();

	@SuppressWarnings("static-access")
	public static Logger getLogger(String nomeDaClasse) {

		return LoggerTMS.getLog(NOME_DO_LOG).getLogger(nomeDaClasse);
	}

}
