package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;



//Aqui é uma interface com Query personalizadas
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries{

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
		
		String jpql = "from Restaurante whrere nome like :nome and taxaFrete between: taxaInicial and taxaFinal";
		//aqui os % % não funciona no JPQL puro, por isso foi colocado nos "setParameter"
		
		return manager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("taxaInicial", "%" + taxaInicial + "%")
				.setParameter("taxaFinal", "%" +  taxaFinal + "%")
				.getResultList();
	}
	
	
	//essa forma é interessante mas se for alterado o nome do método aqui ou no repositório não vai apontar um erro de execução.
	//por isso o ideal é extrair uma interface desta classe fazer o repositório herda-la (é permitido herança multipla no caso de interface)
}
