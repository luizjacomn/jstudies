package com.luizjacomn.concurrency.server;

import java.util.Random;
import java.util.concurrent.ThreadFactory;

import com.luizjacomn.concurrency.exception.handler.ThreadExceptionHandler;

public class CustomThreadFactory implements ThreadFactory {
	
	private static final String[] NAMES = new String[] {"\"veronica\"", "\"carla\"", "\"catarina\"", "\"leticia\"", "\"jessica\"", "\"claudia\"", "\"sheila\"", "\"sara\""};

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, NAMES[new Random().nextInt(NAMES.length - 1)]);
		
		thread.setUncaughtExceptionHandler(new ThreadExceptionHandler());
		
		return thread;
	}

}
