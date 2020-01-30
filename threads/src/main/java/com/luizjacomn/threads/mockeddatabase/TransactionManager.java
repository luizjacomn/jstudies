package com.luizjacomn.threads.mockeddatabase;

public final class TransactionManager {

	public void begin() {
		System.out.println(String.format("%s iniciando a transação...\n", Thread.currentThread().getName()));

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public void commit() {
		System.out.println(String.format("%s finalizando a transação...", Thread.currentThread().getName()));
	}
	
}