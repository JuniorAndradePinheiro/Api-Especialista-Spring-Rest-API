package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController("/estados")
//@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private CadastroEstadoService cadastroEstado;
    
    @GetMapping("/estados")
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }
   
	@GetMapping("/estados/{estadoId}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long estadoId) {
			
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if (estado.isPresent()) {
				return ResponseEntity.ok(estado.get());
		}
		
		return ResponseEntity.notFound().build();			
	}
    

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado){
    	cadastroEstado.salvar(estado);
    	return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

	
	@PutMapping("estados/{estadoId}")
	public ResponseEntity<Estado> alterar(@PathVariable Long estadoId, @RequestBody Estado estado){
		
		Estado estadoAtual = estadoRepository.findById(estadoId).orElse(null);
	
			estadoAtual = cadastroEstado.salvar(estadoAtual);
			return ResponseEntity.ok(estadoAtual);
	
		
		//return ResponseEntity.notFound().build();
	}
    
    
    
    @DeleteMapping("estados/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.excluir(estadoId);
			return ResponseEntity.noContent().build();
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}	

	
}