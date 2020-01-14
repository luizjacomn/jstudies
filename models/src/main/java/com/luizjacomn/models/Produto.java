package com.luizjacomn.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(of = {"nome"})
@Getter
@Setter
@ToString
public class Produto {

	private String nome;
	
	private BigDecimal preco;
	
}