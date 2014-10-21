package br.com.aplicacao.util;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * Gerar log da aplicação
 * 
 * @author Ricardo Braga
 */
public class LoggerTMS {

	private static final String DIRETORIO_TEMPORARIO = "java.io.tmpdir";
	private static final int TOTAL_DE_ARQUIVOS = 10;
	private static final String MASCARA_DO_LOG = "%d{yyyy MMM dd HH:mm:ss,SSS} %-2p [%t] %c{3} - %m - (%F:%L) %n";
	private static final String TAMANHO_MAX_LOG = "100000KB";
	private static final String LOG_FILE_DEFAULT = "aplicacao.log";

	private static Logger rootLogger;

	public static Logger getLog() {
		return getLog(LOG_FILE_DEFAULT);
	}

	public static Logger getLog(String nomeDoArquivoDeLog,String nomeDaClasse,String tipoDeBanco) {

		if (tipoDeBanco != null && tipoDeBanco.equals(TipoDeLog.noMongoDB.value())) { return getDBLog(nomeDoArquivoDeLog); }

		return getLog(nomeDoArquivoDeLog);
	}

	public static Logger getLog(String nomeDoArquivoDeLog) {

		if (rootLogger == null) {

			String tempdir = System.getProperty(DIRETORIO_TEMPORARIO);

			if (! (tempdir.endsWith("/") || tempdir.endsWith("\\"))) {
				tempdir = tempdir + System.getProperty("file.separator");
			}

			nomeDoArquivoDeLog = tempdir + nomeDoArquivoDeLog;

			rootLogger = Logger.getRootLogger();
			rootLogger.setLevel(Level.DEBUG);

			PatternLayout layout = new PatternLayout(MASCARA_DO_LOG);

			rootLogger.addAppender(new ConsoleAppender(layout));

			try {
				RollingFileAppender fileAppender = new RollingFileAppender(layout,nomeDoArquivoDeLog,true);
				fileAppender.setMaxBackupIndex(TOTAL_DE_ARQUIVOS);
				fileAppender.setMaxFileSize(TAMANHO_MAX_LOG);
				rootLogger.addAppender(fileAppender);
				rootLogger.setAdditivity(false);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(">>>>>>> ADICIONADO LOG: " + nomeDoArquivoDeLog + "  <<<<<<<");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			} catch (IOException e) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(">>>>>>> ERRO AO ADICIONAR LOG: " + nomeDoArquivoDeLog + "  <<<<<<<");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				e.printStackTrace();
			}

		}

		return rootLogger;
	}

	public static Logger getDBLog(String nomeDoArquivoDeLog) {
		return null;
	}

	private LoggerTMS() {}

}
