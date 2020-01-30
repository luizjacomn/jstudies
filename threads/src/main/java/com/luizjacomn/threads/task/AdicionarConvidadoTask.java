package com.luizjacomn.threads.task;

import java.util.List;

public class AdicionarConvidadoTask implements Runnable {

	private List<String> lista;
	private int numeroThread;

	public AdicionarConvidadoTask(List<String> lista, int numeroThread) {
		this.lista = lista;
		this.numeroThread = numeroThread;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lista.add(String.format("Thread %d - adicionando convidado: %d...", numeroThread, i));
		}
	}

}
