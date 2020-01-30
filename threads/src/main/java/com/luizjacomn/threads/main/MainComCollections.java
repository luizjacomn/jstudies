package com.luizjacomn.threads.main;

import com.luizjacomn.threads.convidados.ListaConvidados;
import com.luizjacomn.threads.task.AdicionarConvidadoTask;
import com.luizjacomn.threads.task.ImprimirListaConvidadosTask;

public class MainComCollections {

	public static void main(String[] args) {
		ListaConvidados lista = new ListaConvidados();
	
		for (int i = 0; i < 10; i++) {
			new Thread(new AdicionarConvidadoTask(lista, i)).start();
		}
		
		new Thread(new ImprimirListaConvidadosTask(lista)).start();
	}
}