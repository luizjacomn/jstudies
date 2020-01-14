package com.luizjacomn.models;

import java.util.Locale;

import javax.money.CurrencyUnit;

public interface Currencible {
	
	CurrencyUnit getCurrencyUnit();
	
	Locale getLocale();
}