package com.luizjacomn.threads.mockeddatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MockedDataBase {

	private ConnectionPool cp = new ConnectionPool();
	private TransactionManager tx = new TransactionManager();
	private static List<Map<String, Object>> bucket = new ArrayList<>();
	private static int identity = 0;

	public ConnectionPool getPool() {
		return cp;
	}

	public TransactionManager getTransactionManager() {
		return tx;
	}

	public synchronized void insert(String table, Map<String, Object> entity) {
		StringBuilder sql = new StringBuilder("SQL > INSERT INTO ");
		sql.append(table.toUpperCase()).append(" (");

		for (String field : entity.keySet()) {
			sql.append(field.toUpperCase()).append(", ");
		}

		sql.append("ID) VALUES (");

		for (Object value : entity.values()) {
			sql.append(value instanceof String ? "\"" + value + "\"" : value).append(", ");
		}

		sql.append(++identity).append(")");

		System.out.println(sql);

		entity.put("id", identity);
		bucket.add(entity);

		System.out.println(String.format("Entity added with ID: %d\n", identity));
	}

	public void select(String table, int id) {
		System.out.println(String.format("SQL > SELECT * FROM %s WHERE ID = %d\n%s\n", table.toUpperCase(), id,
				bucket.stream().filter(entity -> entity.get("id").equals(id)).findFirst().get()));
	}
}