package com.luizjacomn.concurrency.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TaskProvider implements Runnable {

	private Socket socket;

	public TaskProvider(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			System.out.println(String.format("Fornecendo tarefas para %s", socket));

			Scanner scanner = new Scanner(socket.getInputStream());
			
			while (scanner.hasNextLine()) {
				String comandoRecebido = scanner.nextLine();
				System.out.println(comandoRecebido);
			}

			Thread.sleep(20_000);
		} catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}