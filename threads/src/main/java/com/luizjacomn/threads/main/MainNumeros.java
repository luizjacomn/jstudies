package com.luizjacomn.threads.main;

import java.util.logging.Logger;
import java.util.stream.IntStream;

public class MainNumeros {

	private static final Logger LOGGER = Logger.getLogger(MainNumeros.class.getSimpleName());
	
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			IntStream rangeStream = IntStream.rangeClosed(1, 10);

			rangeStream.forEach(num -> LOGGER.info(String.format("01.%d - %d", Thread.currentThread().getId(), num)));
		});
		
		Thread t2 = new Thread(() -> {
			IntStream rangeStream = IntStream.rangeClosed(1, 10);
			
			rangeStream.forEach(num -> LOGGER.info(String.format("02.%d - %d", Thread.currentThread().getId(), num)));
		});
		
		t1.start();
		t2.start();
	}

}
