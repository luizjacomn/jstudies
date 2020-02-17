package com.luizjacomn.concurrency.command;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class WsCommand implements Callable<String> {

	private PrintStream out;

	public WsCommand(PrintStream out) {
		this.out = out;
	}

	@Override
	public String call() throws InterruptedException {
		System.out.println(String.format("Executando %s...", getClass().getSimpleName()));

		out.println(String.format("Processando %s...", getClass().getSimpleName()));
		
		Thread.sleep(10_000);

		out.println(String.format("%s executado!", getClass().getSimpleName()));

		int i = new Random().nextInt(100) + 1;
		
		return String.valueOf(i);
	}
}