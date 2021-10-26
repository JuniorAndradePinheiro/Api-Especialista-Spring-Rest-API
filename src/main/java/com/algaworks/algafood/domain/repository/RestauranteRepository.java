package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	
	//Essa é uma forma de realizar consultas de forma personalizada, por meio da anotation @Query, onde é passada a consulta JPQL.
	//É indicado fazer fazer desta forma em casos que as palavras chaves não atendam a necessiadade ou os nomes dos métodos fiquem muito confunsos e extensos.
	// Dar uma olhada n aula 5.10. Externalizando consultas JPQL para um arquivo XML
	@Query("from Restaurente where nome like %:nome% and id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long idCozinha);

	
	//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long idCozinha);
	
	
	//É possivel usar mais palavras chaves com funcinalidades. A palavra Count realiza uma contagem dos resultado da query. Count é um prefixo
	int countByCozinhaId (Long cozinha);
	
	
	//tem muitas outras que podemos usar como:
	//Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome); - Retorna somente o primeiro resultado (first é uma Flag Word)
	//List<Restaurante>findTop2ByNomeContaining(String nome); - Limita a quantidade de resultados para 2 itens (os 2 primeiros. TOP é uma Flag Word))
	//boolean existsByNome(String nome); - retorna um boolean caso o parametro existe. Exists é um prefixo.
	
	//O Spring faz o relacionamento do método com a implementação na classe RestauranteRepositoryImpl automaticamente, vendo a assinatura do método e a existencia de uma classe com o mesmo nome da interface + o sufixo Impl
	//Como agora o repositório está herdando a interface da inplementação personalizada, não tem mais a necessidade de ter a assinatura do método
	//List<Restaurante> find (String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
