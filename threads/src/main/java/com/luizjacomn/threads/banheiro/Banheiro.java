package com.luizjacomn.threads.banheiro;

public class Banheiro {

	public void numero1() {
		inicio();
		
		synchronized (this) {
			antes();

			System.out.println(String.format("%s fazendo numero 1...", Thread.currentThread().getName()));

			try {
				// 3 segundos
				Thread.sleep(3_000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

			depois();
		}
	}

	public void numero2() {
		inicio();
		
		synchronized (this) {
			antes();

			System.out.println(String.format("%s fazendo numero 2...", Thread.currentThread().getName()));

			// 5 segundos
			try {
				Thread.sleep(5_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			depois();
		}
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