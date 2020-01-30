package com.luizjacomn.threads.task;

import com.luizjacomn.threads.banheiro.Banheiro;

public class Numero2Task implements Runnable {

	private Banheiro banheiro;

	public Numero2Task(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		banheiro.numero2();
	}

}
