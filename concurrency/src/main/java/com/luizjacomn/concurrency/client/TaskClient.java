package com.luizjacomn.concurrency.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient {

	public static void main(String[] args) throws InterruptedException, IOException {

		Socket socket = new Socket("127.0.0.1", 12345);
		System.out.println("------- CONEXÃO ESTABELECIDA COM O SERVIDOR -------");
		
		Thread enviarComando = new Thread(() -> {
			try {
				Scanner scanner = new Scanner(System.in);
				PrintStream out = new PrintStream(socket.getOutputStream());

				System.out.println("Digite \\q para finalizar...");

				while (scanner.hasNextLine()) {
					String comandoParaEnvio = scanner.nextLine();

					if ("\\q".equals(comandoParaEnvio.trim())) {
						out.println("Encerrando cliente...");						
						break;
					}

					if (socket.isClosed()) {
						System.out.println("Servidor sem resposta...");
						break;
					} else {
						System.out.println("Enviando ao servidor...");
						out.println(comandoParaEnvio);
					}
				}

				out.close();
				scanner.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		Thread receberResposta = new Thread(() -> {
			try {
				Scanner respostaServidor = new Scanner(socket.getInputStream());
				while (respostaServidor.hasNextLine()) {
					System.out.println("Recebendo resposta do servidor...");
					String line = respostaServidor.nextLine();
					System.out.println(String.format("Servidor: %s", socket.getRemoteSocketAddress()));
					System.out.println(String.format("Resposta: %s", line));
				}
				respostaServidor.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		});

		enviarComando.start();
		receberResposta.start();
		
		enviarComando.join();
		
		socket.close();
	}

}