package com.luizjacomn.threads.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.luizjacomn.threads.task.AdicionarConvidadoTask;

public class MainComCollections {

	public static void main(String[] args) throws InterruptedException {
		List<String> listaSincronizada = Collections.synchronizedList(new ArrayList<>());
		List<String> lista = new Vector<>();
	
		for (int i = 0; i < 10; i++) {
			new Thread(new AdicionarConvidadoTask(lista, i)).start();
		}
		
		Thread.sleep(2_000);
		
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(i + " - " + lista.get(i));
		}
	}
}