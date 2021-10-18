package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

//	List<Cozinha> consultaPorNome(String nome);
	
// para o repositório jpa implementar o médodo, basta substiuir o nome da função pelo nome da propriedade, mas vai retornar somente consultas exatas, diferentemente de como se utiliza um "like"
//	List<Cozinha> nome(String nome);
	
	//aqui faz basicamente, acrescentando somente "findBy" prefixo ao nome da propriedade para deixar mais expositivo, transformando o nome do método em uma QUERY. E Por ser um prefixo do data jpa o spring entende e faz a implementação como acima. 
	//Eu posso colocar só findByNome ou findQualquerCoisaByNome desde que estaja entre o find e o By
	List<Cozinha> findTodasByNome(String nome);
	
	
//	Optional<Cozinha> findByNome(String nome);
	
	// Existem palavras chaves que servem para contruir querys acrescentando ao nome do método. A palavre Chave "Containing" faz com que query usa o Like, ou seja, não mais uma consulta exata e sim uma consulta aproximada
	//DOCUMENTAÇÃO CONTENDO TODAS AS PALAVRAS CHAVE
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	Optional<Cozinha> findByNomeContaining(String nome);
	
}
