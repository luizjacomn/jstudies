package com.luizjacomn.concurrency.server;

import java.io.IOException;
import java.io.PrintStream;
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
			
			PrintStream out = new PrintStream(socket.getOutputStream());
			
			while (scanner.hasNextLine()) {
				String comandoRecebido = scanner.nextLine();
				
				switch (comandoRecebido) {
				case "c1":
					out.println("imprimindo c1");
					break;
				case "c2":
					out.println("imprimindo c2");
					break;
				default:
					out.println("Comando não encontrado!");
					break;
				}
				
				System.out.println(String.format("Cliente: %s", socket.getRemoteSocketAddress()));
				System.out.println(String.format("Comando recebido: %s", comandoRecebido));
				System.out.println("------------------------------------------------");
			}

			scanner.close();
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}