package com.luizjacomn.concurrency.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TaskClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("127.0.0.1", 12345);
		System.out.println("------- INICIANDO CLIENTE -------");
			
		Scanner teclado = new Scanner(System.in);
		
		teclado.nextLine();
		
		socket.close();
	}

}