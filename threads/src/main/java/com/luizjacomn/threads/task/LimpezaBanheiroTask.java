package com.luizjacomn.threads.task;

import com.luizjacomn.threads.banheiro.Banheiro;

public class LimpezaBanheiroTask implements Runnable {

	private Banheiro banheiro;

	public LimpezaBanheiroTask(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		while (true) {
			banheiro.limparBanheiro();
			
			try {
				Thread.sleep(15_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
