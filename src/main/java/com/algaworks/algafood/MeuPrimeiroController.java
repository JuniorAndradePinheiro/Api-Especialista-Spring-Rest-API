package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	private AtivacaoClienteService ativacaoClienteService;
		
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente joao = new Cliente("João","joao@xyz.com" ,"13 98756-6566");
		ativacaoClienteService.ativar(joao);
		return "Olá mundo";
	}

}
