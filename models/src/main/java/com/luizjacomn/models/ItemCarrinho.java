package com.luizjacomn.models;

import javax.money.MonetaryAmount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemCarrinho {

	private Produto produto;
	
	private int quantidade;
	
	public MonetaryAmount valor() {
		return produto.getPreco().multiply(quantidade);
	}
}