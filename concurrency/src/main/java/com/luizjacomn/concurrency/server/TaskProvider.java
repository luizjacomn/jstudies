package com.luizjacomn.concurrency.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskProvider implements Runnable {

	private TaskServer server;
	private Socket socket;

	public TaskProvider(TaskServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			System.out.println(String.format("Fornecendo tarefas para %s", socket));

			Scanner scanner = new Scanner(socket.getInputStream());

			PrintStream out = new PrintStream(socket.getOutputStream());

			while (server.isRunning() && scanner.hasNextLine()) {
				String comandoRecebido = scanner.nextLine();

				switch (comandoRecebido) {
				case "c1":
					out.println("imprimindo c1");
					break;
				case "c2":
					out.println("imprimindo c2");
					break;
				case "off":
					server.stop();
					out.println("Servidor desligado!");
					break;
				default:
					out.println("Comando não encontrado!");
					break;
				}

				System.out.println(String.format("Cliente: %s", socket.getRemoteSocketAddress()));
				if (comandoRecebido.equals("Encerrando cliente..."))
					System.out.println(comandoRecebido);
				else
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