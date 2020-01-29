package com.luizjacomn.threads.task;

import java.math.BigInteger;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MultiplicacaoTask implements Runnable {
	private JTextField primeiro;
	private JTextField segundo;
	private JLabel resultado;

	public MultiplicacaoTask(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}

	@Override
	public void run() {
		long valor1 = Long.parseLong(primeiro.getText());
		long valor2 = Long.parseLong(segundo.getText());
		BigInteger calculo = BigInteger.ZERO;

		for (int i = 0; i < valor1; i++) {
			for (int j = 0; j < valor2; j++) {
				calculo = calculo.add(BigInteger.ONE);
			}
		}

		resultado.setText(calculo.toString());
	}

}
