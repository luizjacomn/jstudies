package com.luizjacomn.concurrency.server.result;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WsBdResultHandler implements Callable<Void> {

	private Future<String> futureWS;
	private Future<String> futureBD;
	private PrintStream out;

	public WsBdResultHandler(Future<String> futureWS, Future<String> futureBD, PrintStream out) {
		this.futureWS = futureWS;
		this.futureBD = futureBD;
		this.out = out;
	}

	@Override
	public Void call() {
		System.out.println("Aguardando resultados dos futures...");
		
		try {
			String resultado = String.format("WS: %s - BD: %s", futureWS.get(15, TimeUnit.SECONDS), futureBD.get(10, TimeUnit.SECONDS));
			out.println(resultado);
			System.out.println("Resultado agrupado...");
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("Cancelando execução do comando...");
			out.println("Timeout na execução do comando :/ ...");
			futureWS.cancel(true);
			futureBD.cancel(true);
		}
		
		return null;
	}
}