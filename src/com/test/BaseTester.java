package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTester {
	
	protected static Logger LOGGER = LoggerFactory.getLogger("测试基类日志器");
	private static final AtomicInteger THREAD_COUNT = new AtomicInteger(1);
	
	protected static final ExecutorService EXECUTOR = new ThreadPoolExecutor(10, 50, 1000, 
			TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
				public Thread newThread(Runnable r) {
					Thread t = new Thread(null, r,
							"Test-Thread-"+THREAD_COUNT.getAndIncrement(),
                            0);
					return t;
				}
			});
	
//	protected static final ExecutorService EXECUTOR = Executors.newCachedThreadPool(new ThreadFactory() {
//		public Thread newThread(Runnable r) {
//			Thread thread = new Thread("Test-Thread-"+THREAD_COUNT.getAndIncrement());
//			System.out.println("new thread is getPriority="+thread.getPriority() );
////			thread.setDaemon(true);
//			return thread;
//		}
//	});
	
//	protected static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
	protected static void printThread(Thread t) {
		System.out.println("Thread: t="+t+", id="+t.getId());
	}

	protected static void tPrint(String msg) {
		System.out.println(Thread.currentThread()+", msg="+msg);
	}
	
	protected void logError(Exception ex) {
		LOGGER.error("error", ex);
	}
	
	protected static void tSleep(int t) {
		try {
			Thread.sleep(t);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public BaseTester() {
		super();
	}

}