package com.luizjacomn.threads.convidados;

public class ListaConvidados {

	private String[] convidados = new String[100];
	private int posicao = 0;
	
	public synchronized void addConvidado(String nome) {
		this.convidados[posicao++] = nome;
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (isPreenchida()) {
			System.out.println("lista preenchida, notificando...");
			this.notify();
		}
	}
	
	public String convidadoNaPosicao(int posicao) {
		return this.convidados[posicao];
	}
	
	public int tamanhoMaximo() {
		return this.convidados.length;
	}
	
	public boolean isPreenchida() {
		return posicao == tamanhoMaximo();
	}
}