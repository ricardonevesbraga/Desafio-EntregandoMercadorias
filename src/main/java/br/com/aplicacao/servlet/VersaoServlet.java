package br.com.aplicacao.servlet;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.aplicacao.type.WalmartRotas;
import br.com.aplicacao.util.AmbienteUtil;
import br.com.aplicacao.util.DateUtil;

/**
 * Classe para controle das versões do sistema
 * 
 * @author Ricardo Braga
 */
public class VersaoServlet extends HttpServlet {

	private static final long serialVersionUID = -2836193315449886091L;

	private static final String PAGINA_VERSAO_JSP = "/versao.jsp";
	private static final String TMS = "aplicacao";
	private static final String PROPRIEDADE_JAVA_VERSION = "java.version";
	private static final String CLASSLOADER = "classloader";
	private static final String BIBLIOTECAS = "libs";
	private static final String USUARIO_AUTENTICADO = "autenticado";
	private static final String VERSAO_DO_JAVA = "java";
	private static final String SERVLET = "servlet";
	private static final String SERVIDOR = "servidor";
	private static final String AMBIENTE = "ambiente";
	private static final String BUILD = "build";
	private static final String VERSAO = "versao";
	private static final String NOME_DA_APLICACAO = "nome";
	private static final String UPTIME_EM_DIAS = "uptimeEmDias";
	private static final String UPTIME_EM_HORAS = "uptimeEmHoras";
	private static final String UPTIME_EM_MINUTOS = "uptimeEmMinutos";
	private static final String UPTIME_EM_SEGUNDOS = "uptimeEmSegundos";
	private static final String DATA_DE_STARTUP = "dataDeStartup";
	private static final String PARAMETRO_PWD = "pwd";

	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {

		String senha = request.getParameter(PARAMETRO_PWD);
		if (senhaValida(senha)) {

			RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
			long secondsInMilli = 1000;
			long minutesInMilli = secondsInMilli * 60;
			long hoursInMilli = minutesInMilli * 60;
			long daysInMilli = hoursInMilli * 24;

			long uptime = rb.getUptime();
			long uptimeEmDias = uptime / daysInMilli;
			uptime = uptime % daysInMilli;

			long uptimeEmHoras = uptime / hoursInMilli;
			uptime = uptime % hoursInMilli;

			long uptimeEmMinutos = uptime / minutesInMilli;
			uptime = uptime % minutesInMilli;

			long uptimeEmSegundos = uptime / secondsInMilli;

			request.setAttribute(UPTIME_EM_DIAS,uptimeEmDias);
			request.setAttribute(UPTIME_EM_HORAS,uptimeEmHoras);
			request.setAttribute(UPTIME_EM_MINUTOS,uptimeEmMinutos);
			request.setAttribute(UPTIME_EM_SEGUNDOS,uptimeEmSegundos);
			request.setAttribute(DATA_DE_STARTUP,DateUtil.formataCompleta(new Date(rb.getStartTime())));
			request.setAttribute(NOME_DA_APLICACAO,WalmartRotas.nome.value());
			request.setAttribute(VERSAO,WalmartRotas.versao.value());
			request.setAttribute(BUILD,WalmartRotas.build.value());
			request.setAttribute(AMBIENTE,AmbienteUtil.getAmbienteDetectado());
			request.setAttribute(SERVIDOR,getServletContext().getServerInfo());
			request.setAttribute(SERVLET,getServletContext().getMajorVersion() + "." + getServletContext().getMinorVersion());
			request.setAttribute(VERSAO_DO_JAVA,System.getProperty(PROPRIEDADE_JAVA_VERSION));
			request.setAttribute(USUARIO_AUTENTICADO,true);

			StringBuilder libs = new StringBuilder();

			try {
				Class<? extends VersaoServlet> clazz = getClass();
				ClassLoader cl = clazz.getClassLoader();
				request.setAttribute(CLASSLOADER,cl.toString());
				if (cl instanceof URLClassLoader) {
					URLClassLoader ucl = (URLClassLoader) cl;
					URL[] urls = ucl.getURLs();
					for (int i = 0 ; i < urls.length ; i++) {
						libs.append(i + 1 + " - " + urls[i] + "</br>");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute(BIBLIOTECAS,libs.toString());
		}

		request.getRequestDispatcher(PAGINA_VERSAO_JSP).forward(request,response);
	}

	private boolean senhaValida(String senha) {

		if (senha != null && senha.length() > 0) {
			String senhaDeHoje = TMS + DateUtil.getDiaDoMes();
			if (senha.toLowerCase().equals(senhaDeHoje)) { return true; }
		}

		return false;
	}

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		processRequest(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		processRequest(request,response);
	}

}
