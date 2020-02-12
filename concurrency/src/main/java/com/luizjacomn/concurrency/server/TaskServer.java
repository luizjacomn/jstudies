package com.luizjacomn.concurrency.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServer {
	private ServerSocket serverSocket;
	private ExecutorService threadpool;
	private volatile boolean running = false;

	public TaskServer() throws IOException {
		System.out.println("------- INICIANDO SERVIDOR -------");
		this.serverSocket = new ServerSocket(12345);
		this.threadpool = Executors.newCachedThreadPool();
		this.running = true;
	}

	public void run() throws IOException {
		while (isRunning()) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("CONEXÃO ESTABELECIDA COM NOVO CLIENTE NA PORTA " + socket.getPort());

				TaskProvider provider = new TaskProvider(this, socket);
				threadpool.execute(provider);
			} catch (SocketException e) {
				System.out.println("Desligando o servidor...");
				System.out.println("------------------------------------------------");
			}
		}

	}

	public void stop() throws IOException {
		this.running = false;
		serverSocket.close();
		threadpool.shutdown();
	}

	public boolean isRunning() {
		return running;
	}

	public static void main(String[] args) throws IOException {
		TaskServer server = new TaskServer();
		server.run();
	}
}