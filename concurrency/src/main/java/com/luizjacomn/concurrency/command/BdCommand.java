package com.luizjacomn.concurrency.command;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class BdCommand implements Callable<String> {

	private PrintStream out;

	public BdCommand(PrintStream out) {
		this.out = out;
	}

	@Override
	public String call() throws InterruptedException {
		System.out.println(String.format("Executando %s...", getClass().getSimpleName()));

		out.println(String.format("Processando %s...", getClass().getSimpleName()));

		Thread.sleep(5_000);

		out.println(String.format("%s executado!", getClass().getSimpleName()));
		
		int i = new Random().nextInt(100) + 1;
		
		return String.valueOf(i);
	}
}