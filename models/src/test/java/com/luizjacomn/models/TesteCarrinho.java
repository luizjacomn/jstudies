package com.luizjacomn.models;

import static org.junit.Assert.assertEquals;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

// @formatter:off
public class TesteCarrinho {

	private Carrinho carrinho;
	
	private int desconto;
	
	@Before
	public void setup() {
		carrinho = new Carrinho();
		desconto = 15;
		
		Produto p1 = new Produto("Mouse", Money.of(35.99, carrinho.getCurrencyUnit()));
		Produto p2 = new Produto("Teclado", Money.of(139.99, carrinho.getCurrencyUnit()));
		Produto p3 = new Produto("Monitor", Money.of(347.49, carrinho.getCurrencyUnit()));
		
		ItemCarrinho i1 = new ItemCarrinho(p1, 2);
		ItemCarrinho i2 = new ItemCarrinho(p2, 1);
		ItemCarrinho i3 = new ItemCarrinho(p3, 1);
		
		carrinho.addItem(i1)
				.addItem(i2)
				.addItem(i3);
	}

	@Test
	public void dadoCarrinhoQuandoSomadoEntaoTotalIgual() {
		MonetaryAmount esperado = Monetary.getDefaultAmountFactory()
										.setCurrency(carrinho.getCurrencyUnit())
										.setNumber(559.46).create();
		
		assertEquals(esperado, carrinho.total());
	}
	
	@Test
	public void dadoCarrinhoQuandoFormatadoEntaoIgual() {
		assertEquals("R$ 559,46", carrinho.totalFormatado());
	}
	
	@Test
	public void dadoCarrinhoQuandoAplicadoDescontoEntaoTotalIgual() {
		carrinho.aplicarDesconto(desconto);
		
		MonetaryAmount esperado = Monetary.getDefaultAmountFactory()
										.setCurrency(carrinho.getCurrencyUnit())
										.setNumber(475.541).create();
		
		assertEquals(esperado, carrinho.total());
	}

	@Test
	public void dadoCarrinhoQuandoDescontoFormatadoEntaoTotalIgual() {
		carrinho.aplicarDesconto(desconto);
		
		assertEquals("R$ 475,54", carrinho.totalFormatado());
	}
}
