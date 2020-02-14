package com.luizjacomn.concurrency.exception.handler;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(String.format("Tratando exceção na thread %s.%nMensagem: %s", t.getName(), e.getMessage()));
	}
}