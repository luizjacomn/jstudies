package com.luizjacomn.models;

import java.math.BigDecimal;

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
	
	public BigDecimal valor() {
		return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
	}
}