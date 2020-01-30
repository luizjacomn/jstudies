package com.luizjacomn.threads.task;

import com.luizjacomn.threads.banheiro.Banheiro;

public class Numero1Task implements Runnable {

	private Banheiro banheiro;

	public Numero1Task(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		banheiro.numero1();
	}

}
