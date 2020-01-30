package com.luizjacomn.threads.mockeddatabase;

public final class ConnectionPool implements AutoCloseable {

	public String getConnection() {
		System.out.println(String.format("%s recebendo conexão...", Thread.currentThread().getName()));

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "connection";
    }
	
	@Override
	public void close() {
		System.out.println(String.format("%s fechando conexão...", Thread.currentThread().getName()));
	}
}