package com.luizjacomn.threads.banheiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {
	
	private Lock locker = new ReentrantLock();

	public void numero1() {
		inicio();
		
		locker.lock();

		antes();

		System.out.println(String.format("%s fazendo numero 1...", Thread.currentThread().getName()));

		try {
			// 3 segundos
			Thread.sleep(3_000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		depois();
		
		locker.unlock();
	}

	public void numero2() {
		inicio();
		
		locker.lock();
		
		antes();

		System.out.println(String.format("%s fazendo numero 2...", Thread.currentThread().getName()));

		// 5 segundos
		try {
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		depois();
		
		locker.unlock();
	}
	
	private void inicio() {
		System.out.println(String.format("%s batendo na porta...", Thread.currentThread().getName()));
	}

	private void antes() {
		System.out.println(String.format("\n%s entrando no banheiro...", Thread.currentThread().getName()));
	}

	private void depois() {
		System.out.println(String.format("%s lavando as mãos...", Thread.currentThread().getName()));
		System.out.println(String.format("%s saindo do banheiro...", Thread.currentThread().getName()));
		System.out.println("-----------------------------------------------------------");
	}
}