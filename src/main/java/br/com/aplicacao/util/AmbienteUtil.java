package br.com.aplicacao.util;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import br.com.aplicacao.type.WalmartRotas;

public class AmbienteUtil {

	private static Logger logger = Logger.getLogger(br.com.aplicacao.util.AmbienteUtil.class);

	private static String ambienteDetectado;

	static {
		try {
			if (ServerDetector.isJBoss7() || ServerDetector.isJBoss()) {

				logger.info(" Detectado: JBoss Server");

				ambienteDetectado = System.getProperty(WalmartRotas.variavelAmbiente.value());

			} else if (ServerDetector.isTomcat()) {

				logger.info(" Detectado: Tomcat");

				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");

				ambienteDetectado = (String) envCtx.lookup(WalmartRotas.variavelAmbiente.value());
			}
			logger.info(" Ambiente detectado: " + ambienteDetectado);

		} catch (Exception e) {
			logger.error("Erro ao detectar servidor",e);
		}
	}

	public static String getAmbienteDetectado() {
		return ambienteDetectado;
	}
}
