package com.luizjacomn.threads.convidados;

public class ListaConvidados {

	private String[] convidados = new String[1000];
	private int posicao = 0;
	
	public synchronized void addConvidado(String nome) {
		this.convidados[posicao++] = nome;
	}
	
	public String convidadoNaPosicao(int posicao) {
		return this.convidados[posicao];
	}
	
	public int tamanhoMaximo() {
		return this.convidados.length;
	}
}