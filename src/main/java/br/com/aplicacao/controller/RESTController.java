package br.com.aplicacao.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.aplicacao.dto.Mapa;
import br.com.aplicacao.dto.MelhorCaminho;
import br.com.aplicacao.dto.core.ResultadoREST;
import br.com.aplicacao.exception.ServiceException;
import br.com.aplicacao.service.RESTService;
import br.com.aplicacao.type.RetornoREST;

/**
 * Página de controller dos serviços REST do sistema
 * 
 * @author Ricardo Braga
 */
@Controller
@RequestMapping(value = "/rest/servicos")
public class RESTController {

	@Autowired
	@Qualifier("restService")
	private RESTService restService;

	@RequestMapping(value = "/getMelhorCaminho",method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Object> getMelhorCaminho(@RequestParam(value = "origem") String origem,@RequestParam(value = "destino") String destino,@RequestParam(value = "autonomia") double autonomia,@RequestParam(value = "valorLitro") double valorLitro) {

		MelhorCaminho melhorCaminho = new MelhorCaminho();
		try {
			melhorCaminho = restService.getMelhorCaminho(origem,destino,autonomia,valorLitro);
		} catch (ServiceException e) {
			return new ResponseEntity<Object>(new ResultadoREST(RetornoREST.erroInesperado.codigo(),e.getMessage()),HttpStatus.OK);
		}
		if (melhorCaminho == null) { return new ResponseEntity<Object>(new ResultadoREST(RetornoREST.registroNaoEncontrado.codigo(),RetornoREST.registroNaoEncontrado.mensagem()),HttpStatus.OK); }

		return new ResponseEntity<Object>(melhorCaminho,HttpStatus.OK);
	}

	@RequestMapping(value = "/incluirMapa",method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Object> incluirMapa(@RequestBody ArrayList<Mapa> mapa) {
		try {
			restService.incluirMapa(mapa);
		} catch (ServiceException e) {
			return new ResponseEntity<Object>(new ResultadoREST(RetornoREST.erroInesperado.codigo(),e.getMessage()),HttpStatus.OK);
		}
		return new ResponseEntity<Object>(new ResultadoREST(RetornoREST.sucesso.codigo(),RetornoREST.sucesso.mensagem()),HttpStatus.OK);
	}

}
