package com.luizjacomn.threads.main;

import com.luizjacomn.threads.convidados.ListaConvidados;
import com.luizjacomn.threads.task.AdicionarConvidadoTask;

public class MainComCollections {

	public static void main(String[] args) throws InterruptedException {
		ListaConvidados lista = new ListaConvidados();
	
		for (int i = 0; i < 10; i++) {
			new Thread(new AdicionarConvidadoTask(lista, i)).start();
		}
		
		Thread.sleep(2_000);
		
		for (int i = 0; i < lista.tamanhoMaximo(); i++) {
			System.out.println(i + " - " + lista.convidadoNaPosicao(i));
		}
	}
}