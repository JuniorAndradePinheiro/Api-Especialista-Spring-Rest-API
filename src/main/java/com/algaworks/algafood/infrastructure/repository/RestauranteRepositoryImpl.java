package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;



//Aqui é uma interface com Query personalizadas
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries{

	@PersistenceContext
	EntityManager manager;
		
//Desta forma é possivel realizar consultas dinamicas com a mesma query, passando varios parametros, somente um ou nenhum, que a query vai sendo montada conforme os parametros são preenchidos	
//	@Override
//	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
//		
//		//StringBuilder é uma classe utilitária para manipulação de String
//		var jpql = new StringBuilder("from Restaurante whrere 0=0 ");
//		var parametros = new HashMap<String, Object>();
//		
//		//StringUtils é uma classe utilitária para manipulação de String
//		//hasLength é um método que verfica se a String não é nula e se o comprimento dela não é 0
//		if(StringUtils.hasLength(nome)) {
//			jpql.append("and nome like :nome ");
//			parametros.put("nome", "%" + nome + "%");
//		}
//		
//		if(taxaInicial != null) {
//			//.append é um método da classe StringBuilder para concaternar Strings
//			jpql.append("and taxaFrete >= :taxaInicial ");
//			parametros.put("taxaInicial", "%" + taxaInicial + "%");
//		}
//		
//		if(taxaFinal !=null) {
//			jpql.append("and taxaFrete <= :taxaFinal ");
//			parametros.put("taxaFinal", "%" +  taxaFinal + "%");
//		}
//		
//		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
//		
//		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//		
//		return query.getResultList();
//	}
	

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){

		//criteriaAPI é uma API que permite criar queries com código JAVA. Indicada somente para consultas muito complexas, esse método é mais burocrático.
		
		//É um criador de expressões SQL para o criteriQuery usar.
		CriteriaBuilder builder = manager.getCriteriaBuilder(); 
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class); //nesse trecho informa a classe que você usará pra fazer a query
		
		criteria.from(Restaurante.class); //Equivalente ao JPQL from Restaurante
		
		

		//Usar uma sobrecarga do createQuery para criteria
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
	}	
	
}
