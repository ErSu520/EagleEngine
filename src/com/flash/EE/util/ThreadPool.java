package com.flash.EE.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	
	private static ExecutorService pool = Executors.newCachedThreadPool();
	
	public static ExecutorService getPool() {
		return pool;
	}
	
	
}
