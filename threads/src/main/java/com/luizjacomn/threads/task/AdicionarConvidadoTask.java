package com.luizjacomn.threads.task;

import com.luizjacomn.threads.convidados.ListaConvidados;

public class AdicionarConvidadoTask implements Runnable {

	private ListaConvidados lista;
	private int numeroThread;

	public AdicionarConvidadoTask(ListaConvidados lista, int numeroThread) {
		this.lista = lista;
		this.numeroThread = numeroThread;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lista.addConvidado(String.format("Thread %d - adicionando convidado: %d...", numeroThread, i));
		}
	}

}
