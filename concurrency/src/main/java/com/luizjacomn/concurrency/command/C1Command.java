package com.luizjacomn.concurrency.command;

import java.io.PrintStream;

public class C1Command extends Command implements Runnable {

	public C1Command(PrintStream out) {
		super(out);
	}

	@Override
	public void run() {
		execute(5);
	}

}