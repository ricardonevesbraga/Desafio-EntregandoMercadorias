package br.com.aplicacao.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JNDILocator {

	private static Logger logger = Logger.getLogger(br.com.aplicacao.util.JNDILocator.class);

	private static final Map<String,Object> services = new ConcurrentHashMap<String,Object>();

	private static Context context;

	static {
		try {
			Context initContext = new InitialContext();
			if (ServerDetector.isJBoss7()) {
				logger.info(" Detectado: JBoss Server 7");
				context = (Context) initContext.lookup("java:/comp/env");
			} else if (ServerDetector.isJBoss()) {
				logger.info(" Detectado: JBoss Server");
				context = (Context) initContext.lookup("java:");
			} else if (ServerDetector.isTomcat()) {
				logger.info(" Detectado: Apache Tomcat ");
				context = (Context) initContext.lookup("java:/comp/env");
			} else if (ServerDetector.isTomcat() || ServerDetector.isJetty()) {
				logger.info(" Detectado: Jetty Server");
				context = (Context) initContext.lookup("java:/comp/env");
			} else {
				context = initContext;
			}
		} catch (Exception e) {
			logger.error("Erro ao detectar servidor",e);
		}
	}

	public DataSource getDataSource(String name) {
		if (name == null || name.length() <= 0) { throw new IllegalArgumentException("name"); }

		if (services.containsKey(name)) { return (DataSource) services.get(name); }

		DataSource ds = null;
		try {
			ds = (DataSource) context.lookup(name);
			services.put(name,ds);
		} catch (NamingException e) {
			logger.error("Data Source nao encontrado ! ",e);
		}

		return ds;
	}

	public Connection getConnectionFromDataSource(String dataSourceName) {

		DataSource ds = getDataSource(dataSourceName);
		Connection connection = null;

		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			logger.error("Erro ao buscar connection! ",e);
		}

		return connection;

	}

}
