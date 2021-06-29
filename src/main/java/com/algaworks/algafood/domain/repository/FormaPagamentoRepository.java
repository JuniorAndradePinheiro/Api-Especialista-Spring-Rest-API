package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository {
	
	List<FormaPagamento> listar();
	FormaPagamento buscar (Long id);
	FormaPagamento Salvar(FormaPagamento formaPagamento);
	void remover (FormaPagamento formagamento);

}
