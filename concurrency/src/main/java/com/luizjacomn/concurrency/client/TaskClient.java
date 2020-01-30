package com.luizjacomn.concurrency.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TaskClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("127.0.0.1", 12345);
		System.out.println("------- CONEXÃO ESTABELECIDA COM O SERVIDOR -------");

		PrintStream out = new PrintStream(socket.getOutputStream());
		out.println("c1");
		
		Scanner teclado = new Scanner(System.in);
		teclado.nextLine();
		
		out.close();
		teclado.close();
		socket.close();
	}

}