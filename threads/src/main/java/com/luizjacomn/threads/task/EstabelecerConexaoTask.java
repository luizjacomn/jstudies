package com.luizjacomn.threads.task;

import java.util.HashMap;
import java.util.Map;

import com.luizjacomn.threads.mockeddatabase.ConnectionPool;
import com.luizjacomn.threads.mockeddatabase.MockedDataBase;
import com.luizjacomn.threads.mockeddatabase.TransactionManager;

public class EstabelecerConexaoTask implements Runnable {

	private MockedDataBase db;

	public EstabelecerConexaoTask(MockedDataBase db) {
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
					
					Map<String, Object> tcross = new HashMap<>();
					tcross.put("nome", "T-Cross");
					tcross.put("preco", 54_000.0);
					tcross.put("fabricante", "VolksWagen");
					
					db.insert("carros", tcross);
					
					Map<String, Object> onix = new HashMap<>();
					onix.put("nome", "Onix");
					onix.put("preco", 47_999.0);
					onix.put("fabricante", "Chevrolet");
					
					db.insert("carros", onix);
					
					tx.commit();
				}
			}
		}
	}

}