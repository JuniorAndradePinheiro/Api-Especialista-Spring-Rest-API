package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Primary  Como tem mais de uma implementação de notificador essa notação informa para o spring qual ele deve usar no momento
//@Qualifier("urgente") //Substitui o primary mas deixa mais explcito qual notificado estou usando no Controller(tem que colocar a memsa notação no Controller)

@Profile("dev")//Essa notation define o perfil ou ambiente que está lasse ou método deve rodar(Desenvolvimetno ou produção)
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component //Aqui voltou a ser notado com Component ignorando o a classe classe NotificacaoConfig
public class NotificadorEmailMock implements Notificador {
	
	@Autowired
	private NotificadorProperties properties;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
			System.out.println("Servidor: " + properties.getHostServidor());
			System.out.println("Porta: " + properties.getPortaServidor());
			
			System.out.printf("Mock: Notificação seria enviada para %s através do e-mail %s: %s \n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
