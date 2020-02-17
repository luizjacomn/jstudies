package com.luizjacomn.concurrency.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public final class TaskServer {

	private static final Integer QTT_CONSUMERS = 2;

	private ServerSocket serverSocket;
	private ExecutorService threadpool;
	private AtomicBoolean running;
	private BlockingQueue<String> filaDeComandos;

	public TaskServer() throws IOException {
		System.out.println("------- INICIANDO SERVIDOR -------");
		this.serverSocket = new ServerSocket(12345);
		this.threadpool = Executors.newCachedThreadPool(new CustomThreadFactory());
		this.running = new AtomicBoolean(true);
		this.filaDeComandos = new ArrayBlockingQueue<>(QTT_CONSUMERS);
		startConsumers();
	}

	private void startConsumers() {
		for (int i = 0; i < QTT_CONSUMERS; i++) {
			TaskConsumer consumer = new TaskConsumer(filaDeComandos);
			threadpool.execute(consumer);
		}
	}

	public final void run() throws IOException {
		while (isRunning()) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("CONEXÃO ESTABELECIDA COM NOVO CLIENTE NA PORTA " + socket.getPort());

				TaskProvider provider = new TaskProvider(this, socket, threadpool, filaDeComandos);
				threadpool.execute(provider);
			} catch (SocketException e) {
				System.out.println("Desligando o servidor...");
				System.out.println("------------------------------------------------");
			}
		}

	}

	public final void stop() throws IOException {
		this.running.set(false);
		serverSocket.close();
		threadpool.shutdown();
	}

	public final boolean isRunning() {
		return running.get();
	}

	public static void main(String[] args) throws IOException {
		TaskServer server = new TaskServer();
		server.run();
	}
}