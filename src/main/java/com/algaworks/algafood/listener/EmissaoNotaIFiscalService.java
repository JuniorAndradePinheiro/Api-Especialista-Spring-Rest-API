package com.algaworks.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;
import com.algaworks.algafood.di.service.ClienteAtivadoEvent;
@Component
public class EmissaoNotaIFiscalService {
	
//	@Qualifier("urgente")
	@Autowired(required = false)
	@TipoDoNotificador(NivelUrgencia.URGENTE) //Substitui o primary mas deixa mais explicito qual notificado estou usando no Controller(tem que colocar a memsa notação no Controller)
	private Notificador notificador; 
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		System.out.println("Emitindo NF do cliente " + event.getCliente().getNome());
	}
}
