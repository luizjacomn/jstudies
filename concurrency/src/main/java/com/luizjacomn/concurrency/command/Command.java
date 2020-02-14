package com.luizjacomn.concurrency.command;

import java.io.PrintStream;

public abstract class Command {

	protected PrintStream out;
	
	public Command(PrintStream out) {
		this.out = out;
	}

	private long time(int seconds) {
		return (long) seconds * 1_000;
	}
	
	protected void execute(int seconds) {
		System.out.println(String.format("Executando %s...", getClass().getSimpleName()));

		try {
			Thread.sleep(time(seconds));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("exceção no comando");
//		out.println(String.format("%s executado!", getClass().getSimpleName()));
	}
	
}