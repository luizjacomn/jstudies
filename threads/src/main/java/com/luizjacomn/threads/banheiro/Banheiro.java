package com.luizjacomn.threads.banheiro;

public class Banheiro {

	private boolean isSujo = true;

	public void numero1() {
		String nome = Thread.currentThread().getName();

		inicio(nome);

		synchronized (this) {

			while (isSujo) {
				esperaLimpeza(nome);
			}

			antes(nome);
			System.out.println(String.format("%s fazendo numero 1...", Thread.currentThread().getName()));

			// 3 segundos
			aguardar(3);

			depois(nome);
		}
	}

	public void numero2() {
		String nome = Thread.currentThread().getName();

		inicio(nome);

		synchronized (this) {
			while (isSujo) {
				esperaLimpeza(nome);
			}

			antes(nome);
			System.out.println(String.format("%s fazendo numero 2...", nome));

			// 5 segundos
			aguardar(5);

			depois(nome);
		}
	}

	public void limparBanheiro() {
		String nome = Thread.currentThread().getName();

		inicio(nome);

		synchronized (this) {
			if (!isSujo) {
				System.out.println(String.format("%s: banheiro não está sujo, não preciso limpar...", nome));
				System.out.println("-----------------------------------------------------------");
				return;
			}

			antes(nome);
			System.out.println(String.format("%s limpando banheiro...", nome));
			this.isSujo = false;

			// 10 segundos
			aguardar(10);

			this.notifyAll();
			
			System.out.println(String.format("%s saindo do banheiro...", nome));
			System.out.println("-----------------------------------------------------------");
		}
	}

	private void inicio(String nome) {
		System.out.println(String.format("%s batendo na porta...", nome));
	}

	private void antes(String nome) {
		System.out.println(String.format("\n%s entrando no banheiro...", nome));
	}

	private void depois(String nome) {
		this.isSujo = true;
		
		System.out.println(String.format("%s dando descarga...", nome));
		System.out.println(String.format("%s lavando as mãos...", nome));
		System.out.println(String.format("%s saindo do banheiro...", nome));
		System.out.println("-----------------------------------------------------------");
	}

	private void esperaLimpeza(String nome) {
		System.out.println(String.format("%s: ecaa... Banheiro está sujo!", nome));
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void aguardar(int segundos) {
		try {
			Thread.sleep(segundos * 1_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}