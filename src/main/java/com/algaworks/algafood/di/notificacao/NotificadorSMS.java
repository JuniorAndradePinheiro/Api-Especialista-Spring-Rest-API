package com.algaworks.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Qualifier("normal") //Substitui o primary mas deixa mais explcito qual notificado estou usando no Controller(tem que colocar a memsa notação no Controller)

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA) //"Substitui" o uso do @Qualifier por uma notação criada por mim"@TipoDoNotificador" usando um ENUM como critétio
@Component 
public class NotificadorSMS implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
			System.out.printf("Notificando %s por SMS através do telefone  %s: %s\n", 
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}
}
