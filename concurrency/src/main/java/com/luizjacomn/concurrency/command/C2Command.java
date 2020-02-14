package com.luizjacomn.concurrency.command;

import java.io.PrintStream;

public class C2Command extends Command implements Runnable {


	public C2Command(PrintStream out) {
		super(out);
	}

	@Override
	public void run() {
		execute(10);
	}
}