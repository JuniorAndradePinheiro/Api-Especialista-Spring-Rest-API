package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;



//Aqui é uma interface com Query personalizadas
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries{

	@PersistenceContext
	EntityManager manager;
	
	
//essa forma é interessante mas se for alterado o nome do método aqui ou no repositório não vai apontar um erro de execução.
//por isso o ideal é extrair uma interface desta classe fazer o repositório herda-la (é permitido herança multipla no caso de interface)
	
//	@Override
//	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
//		
//		String jpql = "from Restaurante whrere nome like :nome and taxaFrete between: taxaInicial and taxaFinal";
//		//aqui os % % não funciona no JPQL puro, por isso foi colocado nos "setParameter"
//		
//		return manager.createQuery(jpql, Restaurante.class)
//				.setParameter("nome", "%" + nome + "%")
//				.setParameter("taxaInicial", "%" + taxaInicial + "%")
//				.setParameter("taxaFinal", "%" +  taxaFinal + "%")
//				.getResultList();
//	}
	
	
//Desta forma é possivel realizar consultas dinamicas com a mesma query, passando varios parametros, somente um ou nenhum, que a query vai sendo montada conforme os parametros são preenchidos	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
		
		//StringBuilder é uma classe utilitária para manipulação de String
		var jpql = new StringBuilder("from Restaurante whrere 0=0 ");
		var parametros = new HashMap<String, Object>();
		
		//StringUtils é uma classe utilitária para manipulação de String
		//hasLength é um método que verfica se a String não é nula e se o comprimento dela não é 0
		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		
		if(taxaInicial != null) {
			//.append é um método da classe StringBuilder para concaternar Strings
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", "%" + taxaInicial + "%");
		}
		
		if(taxaFinal !=null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", "%" +  taxaFinal + "%");
		}
		
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		
		return query.getResultList();
	}
	
	
}
