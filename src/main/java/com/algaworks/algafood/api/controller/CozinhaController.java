package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	//@GetMapping(produces = "Application/xml") Com essa propriedade Produces é posso especificar aqui qual tipo de mídia ele o método vai retornar mesmo que a aplicação ou requisição esteja com figurada com outro tipo
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
	public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long cozinhaId) {
	//ResponseEntity é uma classe que nos permite criar respostas HTTP, acrescentando códigos de status, corpo da resposta e cabeçalhos	
		
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		//return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		
		if (cozinha != null) {
			//é basicamente a forma simplificada da linha mais acima
			return ResponseEntity.ok(cozinha);
		}
		
		return ResponseEntity.notFound().build();
				
//		//Retorna  o 302 (quando a recurso foi movido para outra URI) e está fazendo u mredirecinamento para URI temporaria (no caso a lista de cozinhas)
//		HttpHeaders header = new HttpHeaders();
//		header.add(HttpHeaders.LOCATION, "/cozinhas");
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(header)
//				.build();
	}
	
//	Essa forma também está correta
//	@PostMapping
//	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha){
//		cozinhaRepository.salvar(cozinha);
//		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
//		
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha){
		return cozinhaRepository.salvar(cozinha);
	}
	
	
	@PutMapping("/{cozinhaId}" )
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinhaAtual != null){
			//cozinhaAtual.setNome(cozinha.getNome()); // essa forma está correta mas se tivesse 10 atributos eu teria que fazer isso para cada um
			//essa forma facilita muito porque ela pega as propriedade de um objeto copia para outro. OBS: esse método copia tudo, e como não passamos o id no corpo do JSON ele vai estar nulo quando for copiado, por isso tem ("id"), dessa forma eu estou dizendo para ignorar o id na hora de copiar 
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaAtual = cozinhaRepository.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);
		}
	
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
		try {
			Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
			
			if (cozinha != null) {
				cozinhaRepository.remover(cozinha);
				
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
