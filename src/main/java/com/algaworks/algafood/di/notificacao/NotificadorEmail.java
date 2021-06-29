package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//Essa notation define o perfil ou ambiente que está classe ou método deve rodar(Desenvolvimetno ou produção)
@Profile("prod")
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties properties; 
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("Servidor: " + properties.getHostServidor());
		System.out.println("Porta: " + properties.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s: %s \n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
