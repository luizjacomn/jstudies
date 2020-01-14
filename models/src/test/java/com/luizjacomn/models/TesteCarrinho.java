package com.luizjacomn.models;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

//@formatter:off
public class TesteCarrinho {

	private Carrinho carrinho;
	
	@Before
	public void setup() {
		carrinho = new Carrinho();
		
		Produto p1 = new Produto("Mouse", BigDecimal.valueOf(35.99));
		Produto p2 = new Produto("Teclado", BigDecimal.valueOf(139.99));
		Produto p3 = new Produto("Monitor", BigDecimal.valueOf(347.49));
		
		ItemCarrinho i1 = new ItemCarrinho(p1, 2);
		ItemCarrinho i2 = new ItemCarrinho(p2, 1);
		ItemCarrinho i3 = new ItemCarrinho(p3, 1);
		
		carrinho.addItem(i1)
				.addItem(i2)
				.addItem(i3);
	}
	
	@Test
	public void dadoCarrinhoQuandoSomadoEntaoTotalIgual() {
		assertEquals(BigDecimal.valueOf(559.46), carrinho.total());
	}

}
