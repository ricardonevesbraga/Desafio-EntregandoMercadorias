package br.com.aplicacao.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.aplicacao.type.Paginas;
import br.com.aplicacao.util.LogUtil;

/**
 * Controller da página HOME do sistema
 * 
 * @author Ricardo Braga
 */
@Controller
public class HomeController {

	private static Logger logger = LogUtil.getLogger(br.com.aplicacao.controller.HomeController.class.getName());

	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String home(Locale locale,ModelMap model,HttpServletRequest request) throws Exception {

		try {
			logger.info("Carregando HOME...");
		} catch (final Exception e) {
			model.put("mensagem",e.getMessage());
			model.put("stacktrace",ExceptionUtils.getFullStackTrace(e));
			logger.error("Erro",e);
			return Paginas.error.name();
		}
		return Paginas.home.name();
	}

}
