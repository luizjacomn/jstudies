package com.luizjacomn.threads.task;

import com.luizjacomn.threads.mockeddatabase.ConnectionPool;
import com.luizjacomn.threads.mockeddatabase.MockedDataBase;
import com.luizjacomn.threads.mockeddatabase.TransactionManager;

public class ExecutarProcedureTask implements Runnable {

	private MockedDataBase db;

	public ExecutarProcedureTask(MockedDataBase db) {
		this.db = db;
	}

	@Override
	public void run() {
		try (ConnectionPool pool = db.getPool()) {
			TransactionManager tx = db.getTransactionManager();

			synchronized (pool) {
				System.out.println(String.format("%s estabelecendo conexão...", Thread.currentThread().getName()));
				pool.getConnection();

				synchronized (tx) {
					tx.begin();

					db.select("carros", 1);
					db.select("carros", 2);
					
					tx.commit();
				}
			}
		}
	}
}