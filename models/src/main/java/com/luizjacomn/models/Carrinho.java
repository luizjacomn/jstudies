package com.luizjacomn.models;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryFormats;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @formatter:off
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Carrinho implements Currencible {

	private Set<ItemCarrinho> itens = new HashSet<>();
	
	private int porcentagem;

	public Carrinho addItem(ItemCarrinho item) {
		this.itens.add(item);
		return this;
	}

	public MonetaryAmount total() {
		MonetaryAmount reduce = this.itens.stream()
					.map(ItemCarrinho::valor)
					.reduce(Money.of(0, getCurrencyUnit()),
							(subtotal, elemento) -> subtotal.add(elemento));
		return reduce.subtract(reduce.multiply(porcentagem).divide(100));
	}

	public String totalFormatado() {
		return MonetaryFormats.getAmountFormat(
				AmountFormatQueryBuilder.of(getLocale()).set(CurrencyStyle.SYMBOL).set("pattern", "¤ #,##0.00").build())
				.format(total());
	}
	
	public void aplicarDesconto(int porcentagem) {
		this.porcentagem = porcentagem;
	}

	@Override
	public CurrencyUnit getCurrencyUnit() {
		return Monetary.getCurrency("BRL");
	}

	@Override
	public Locale getLocale() {
		return new Locale("pt", "BR");
	}
}