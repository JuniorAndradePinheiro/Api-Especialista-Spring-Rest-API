package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonRootName("cozinhas")
//@JacksonXmlRootElement("Lista_de_Cozinhas") Tem o mesmo efeito
public class CozinhasXmlWrapper {
	
	@JacksonXmlElementWrapper(useWrapping = false) // estou desabilitando o "embrulho" da lista e abaoxo defindo somente o "embrulho" do item
	@JsonProperty("cozinha")
	private List<Cozinha> cozinhas;

	public CozinhasXmlWrapper(List<Cozinha> cozinhas) {
		this.cozinhas = cozinhas;
	}
	
	public List<Cozinha> getCozinhas() {
		return cozinhas;
	}

	public void setCozinhas(List<Cozinha> cozinhas) {
		this.cozinhas = cozinhas;
	}
	
}
