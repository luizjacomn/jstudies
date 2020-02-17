package com.luizjacomn.concurrency.command;

import java.io.PrintStream;

public class C1Command implements Runnable {
	private PrintStream out;

	public C1Command(PrintStream out) {
		this.out = out;
	}

	@Override
	public void run() {
		System.out.println(String.format("Executando %s...", getClass().getSimpleName()));

		try {
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		out.println(String.format("%s executado!", getClass().getSimpleName()));

	}

}
