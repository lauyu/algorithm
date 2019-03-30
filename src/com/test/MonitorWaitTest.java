package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MonitorWaitTest extends BaseTester {

	private static AtomicInteger stat = new AtomicInteger(0);
	
	static ExecutorService exector = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws Exception {
		MonitorLockTest test = new MonitorLockTest();
//		test.wait();
//		exector.submit(task)
		new Task(test, true).start();
		new Task(test, false).start();
	}

	static class Task extends Thread {
		Object monitor;
		boolean action;
		public Task(Object monitor, boolean action) {
			this.monitor = monitor;
			this.action = action;
		}
		
		public void run() {
			printThread(Thread.currentThread());
			while (true) {
				synchronized (monitor) {
					System.out.println(Thread.currentThread()+" enter monitor");
					if(stat.incrementAndGet() == 50) {
						break;
					}
					if(action) {
						try {							
							System.out.println(Thread.currentThread()+" monitor await... stat="+stat);
//							tSleep(1000);
							monitor.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					} else {
//						stat = 0;
						System.out.println(Thread.currentThread()+" monitor nofity... stat="+stat);
//						tSleep(1000);
						monitor.notify();
						Thread.yield();
						tSleep(100);
					}
					System.out.println(Thread.currentThread()+" exit monitor");	
				}
			}
			
//			printThread(Thread.currentThread());
		}
	}
}
