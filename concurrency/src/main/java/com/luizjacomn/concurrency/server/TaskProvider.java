package com.luizjacomn.concurrency.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.luizjacomn.concurrency.command.BdCommand;
import com.luizjacomn.concurrency.command.C1Command;
import com.luizjacomn.concurrency.command.WsCommand;
import com.luizjacomn.concurrency.server.result.WsBdResultHandler;

public class TaskProvider implements Runnable {

	private TaskServer server;
	private Socket socket;
	private ExecutorService threadpool;

	public TaskProvider(TaskServer server, Socket socket, ExecutorService threadpool) {
		this.server = server;
		this.socket = socket;
		this.threadpool = threadpool;
	}

	@Override
	public void run() {
		try {
			System.out.println(String.format("Fornecendo tarefas para %s", socket));

			Scanner scanner = new Scanner(socket.getInputStream());

			PrintStream out = new PrintStream(socket.getOutputStream());

			while (server.isRunning() && scanner.hasNextLine()) {
				String comandoRecebido = scanner.nextLine();

				System.out.println(String.format("Cliente: %s", socket.getRemoteSocketAddress()));
				if (comandoRecebido.equals("Encerrando cliente...")) {
					System.out.println(comandoRecebido);
					break;
				} else {
					System.out.println(String.format("Comando recebido: %s", comandoRecebido));
				}
				
				System.out.println("------------------------------------------------");

				switch (comandoRecebido) {
				case "c1":
					C1Command c1 = new C1Command(out);
					threadpool.execute(c1);
					break;
				case "c2":
					WsCommand c2WS = new WsCommand(out);
					BdCommand c2BD = new BdCommand(out);
					Future<String> futureWS = threadpool.submit(c2WS);
					Future<String> futureBD = threadpool.submit(c2BD);
					
					threadpool.submit(new WsBdResultHandler(futureWS, futureBD, out));
					break;
				case "off":
					server.stop();
					out.println("Servidor desligado!");
					break;
				default:
					out.println("Comando não encontrado!");
					break;
				}
			}

			scanner.close();
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}