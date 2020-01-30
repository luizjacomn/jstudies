package com.luizjacomn.threads.task;

import com.luizjacomn.threads.convidados.ListaConvidados;

public class ImprimirListaConvidadosTask implements Runnable {

	private ListaConvidados lista;

	public ImprimirListaConvidadosTask(ListaConvidados lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		synchronized (lista) {
			// só deve aguardar caso a lista não tenha sido preenchida
			if (!lista.isPreenchida()) {
				try {
					System.out.println("aguardando preenchimento da lista...");
					lista.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
			for (int i = 0; i < lista.tamanhoMaximo(); i++) {
				System.out.println(i + " - " + lista.convidadoNaPosicao(i));
			}
		}
	}
}