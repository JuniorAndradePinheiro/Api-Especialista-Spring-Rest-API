package com.algaworks.algafood.domain.service;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
//Funciona dessa forma
	
//	public Restaurante salvar(@RequestBody Restaurante restaurante){
//		Long cozinhaId = restaurante.getCozinha().getId();
//		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
//		
//		if(cozinha.isEmpty()) {
//			throw new EntidadeNaoEncontradaException(
//					String.format("Não foi laocalizada nenhuma cozinha com esse ID %d",cozinhaId));
//		}
//		
//		restaurante.setCozinha(cozinha.get());
//
//		return restauranteRepository.Salvar(restaurante);
//	}
	


//Mesmo método simplificado com Lamda
	
	public Restaurante salvar(@RequestBody Restaurante restaurante){
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format("Não foi laocalizada nenhuma cozinha com esse ID %d",cozinhaId)));
		
		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}
		
}
