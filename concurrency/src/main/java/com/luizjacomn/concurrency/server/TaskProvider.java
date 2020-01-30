package com.luizjacomn.concurrency.server;

import java.net.Socket;

public class TaskProvider implements Runnable {

	private Socket socket;

	public TaskProvider(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		
		System.out.println(String.format("Fornecendo tarefas para %s", socket));
		
		try {
			Thread.sleep(20_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}