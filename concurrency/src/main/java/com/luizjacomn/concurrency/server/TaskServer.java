package com.luizjacomn.concurrency.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServer {

	public static void main(String[] args) throws IOException {
		
		System.out.println("------- INICIANDO SERVIDOR -------");
		ServerSocket server = new ServerSocket(12345);
		
//		ExecutorService threadpool = Executors.newFixedThreadPool(2);
		ExecutorService threadpool = Executors.newCachedThreadPool();
		
		while (true) {
			Socket socket = server.accept();
			System.out.println("CONEXÃO ESTABELECIDA COM NOVO CLIENTE NA PORTA " + socket.getPort());
		
			TaskProvider provider = new TaskProvider(socket);
			threadpool.execute(provider);
		}
	}
	
}