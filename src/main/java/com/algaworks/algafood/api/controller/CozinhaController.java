package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;



@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired(required = false)
	private CozinhaRepository cozinhaRepository; 
	
	//@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	//@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	//@GetMapping(produces = "Application/xml") Com essa propriedade Produces é possi especificar aqui qual tipo de mídia ele o método vai retornar mesmo que a aplicação ou requisição esteja com figurada com outro tipo
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Cozinha> listar(){
		return cozinhaRepository.listar();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXML(){
		return new CozinhasXmlWrapper(cozinhaRepository.listar());
	}
	
	
//	Assim está correto também
//	@GetMapping("/{cozinhaId}")
//	public Cozinha buscarPorId(@PathVariable("cozinhaId") Long id) {
//		return cozinhaRepository.buscar(id);
//	}
	
	@GetMapping("/{cozinhaId}")
	public Cozinha buscarPorId(@PathVariable Long cozinhaId) {
		return cozinhaRepository.buscar(cozinhaId);
	}
}
