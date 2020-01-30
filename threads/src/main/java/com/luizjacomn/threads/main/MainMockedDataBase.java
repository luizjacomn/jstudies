package com.luizjacomn.threads.main;

import com.luizjacomn.threads.mockeddatabase.MockedDataBase;
import com.luizjacomn.threads.task.EstabelecerConexaoTask;
import com.luizjacomn.threads.task.ExecutarProcedureTask;

public class MainMockedDataBase {

	public static void main(String[] args) {
		MockedDataBase db = new MockedDataBase();
		
		new Thread(new EstabelecerConexaoTask(db), "DEV Junior").start();
		new Thread(new ExecutarProcedureTask(db), "DEV Senior").start();
	}
	
}