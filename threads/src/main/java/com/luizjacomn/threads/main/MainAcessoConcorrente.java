package com.luizjacomn.threads.main;

import com.luizjacomn.threads.banheiro.Banheiro;
import com.luizjacomn.threads.task.Numero1Task;
import com.luizjacomn.threads.task.Numero2Task;

public class MainAcessoConcorrente {
	
	public static void main(String[] args) {
		Banheiro banheiro = new Banheiro();
		
		Thread foliao1 = new Thread(new Numero1Task(banheiro), "Jessyca");
		Thread foliao2 = new Thread(new Numero2Task(banheiro), "Raul");
		
		foliao1.start();
		foliao2.start();
	}
}