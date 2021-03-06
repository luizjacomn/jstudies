package com.luizjacomn.threads.main;

import com.luizjacomn.threads.task.BuscarEmArquivo;

public class MainBusca {
	public static void main(String[] args) {
		String busca = "nar";

		Thread buscaJogadores = new Thread(new BuscarEmArquivo("jogadores-futebol.txt", busca));
		Thread buscaPilotos = new Thread(new BuscarEmArquivo("pilotos.txt", busca));
		Thread buscaPersonagens = new Thread(new BuscarEmArquivo("personagens-animes.txt", busca));
		
		buscaJogadores.start();
		buscaPilotos.start();
		buscaPersonagens.start();
	}

}