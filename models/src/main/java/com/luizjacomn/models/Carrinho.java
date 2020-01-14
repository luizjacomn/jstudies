package com.luizjacomn.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @formatter:off
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Carrinho {

	private Set<ItemCarrinho> itens = new HashSet<>();

	public Carrinho addItem(ItemCarrinho item) {
		this.itens.add(item);
		return this;
	}

	public BigDecimal total() {
		return this.itens.stream()
					.map(ItemCarrinho::valor)
					.reduce(BigDecimal.ZERO,
							(subtotal, elemento) -> subtotal.add(elemento));
	}
}