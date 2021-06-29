package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Gastronomia") //Caso eu queira eu consigo alterar o nome da classe na represenção do dominio com essa anotation no caso de XML
@Entity
public class Cozinha {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore Com essa anotation eu consigo tirar da rerepsntação da resposta esse elemento quando for montar o JSON  
	//@JsonProperty("Título") Com essa anotation eu consigo personazar o nome da proprienda conforme minha necessidade ou regra de negocio 
	@Column(length = 30)
	private String nome;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cozinha other = (Cozinha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
