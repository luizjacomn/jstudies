package com.luizjacomn.concurrency.server;

import java.util.concurrent.BlockingQueue;

public class TaskConsumer implements Runnable {
	
	private BlockingQueue<String> filaDeComandos;

	public TaskConsumer(BlockingQueue<String> filaDeComandos) {
		this.filaDeComandos = filaDeComandos;
	}

	@Override
	public void run() {

		try {
			String comando = null;
			while ((comando = filaDeComandos.take()) != null) {
				System.out.println("Consumindo: " + comando + " de " + Thread.currentThread().getName());
				Thread.sleep(5_000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}