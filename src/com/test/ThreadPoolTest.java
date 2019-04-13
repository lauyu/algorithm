package com.test;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.stream.IntStream;

public class ThreadPoolTest extends BaseTester {

	private static AtomicInteger T_COUNTER = new AtomicInteger(1);
	private static AtomicInteger TASK_COUNTER = new AtomicInteger(1);
	
	public static void main(String[] args) {
		CompletionService<String> cService = new ExecutorCompletionService(Executors.newFixedThreadPool(5));
		IntStream.of(100, 3).forEach(t -> cService.);
		
		
//		
//		ExecutorService executorService = new ThreadPoolExecutor(2, 7,
//                60L, TimeUnit.SECONDS,
////                new SynchronousQueue<Runnable>(),
//                new ArrayBlockingQueue<>(4),
////                new LinkedBlockingQueue<>(),
//                new ThreadFactory() {
//					@Override
//					public Thread newThread(Runnable r) {
//						return new Thread(r, "Test-Thread-"+T_COUNTER.getAndIncrement());
//					}
//				},
//                new RejectedExecutionHandler() {
//					@Override
//					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//						tPrint("xxxxxxxxxx, reject, T_COUNTER="+T_COUNTER.get()+", task="+r);
//					}
//				});
//		for(int i=0;i<10;i++) {
//			executorService.submit(new SleepTask(2000,i));
//		}
	}

	
	static class StringCallable implements Callable<String> {
		private int t;
		public StringCallable(int t) {
			this.t=t;
		}
		@Override
		public String call() throws Exception {
			if(TASK_COUNTER != null) TASK_COUNTER.wait();
			if(t > 0)
				tSleep(t);
			return "Task-sleep="+t;
		}
		
	}
	
	static class SleepTask implements Runnable {
		private int t;
		private int taskNo; 
		public SleepTask(int t,int taskNo) {
			this.t = t;
			this.taskNo = taskNo;
		}
		@Override
		public void run() {
			tPrint(">>> begin sleep, t="+t + " taskNo=" + taskNo); 
//			toString();
			tSleep(t);
			tPrint("<<< end sleep, t="+t + "taskNo=" + taskNo);
		}
		
		public String toString() {
			return "SleepTask-"+taskNo;
		}
	}
	
	static class ValidMaxPoolSizeHandler implements RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			tPrint("enter reject method");
//			executor.
		}
		
	}
}
