package com.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;

public class Test {
	
	private static void tPrint(String s) {
		System.out.println(Thread.currentThread()+ " " +s);
	}
	
	private static void sleep(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
//		List<?> list = new ArrayList<>();
//		list.add(1);
//		list.add(new Integer(1));
//		list.add(new Object());
//		list.get(1);
		
//		list = new ArrayList<String>();
		
		List<? extends Integer> list = new ArrayList<>();
//		list.add(new Integer(1));
//		test(list);
//		Thread
//		ReentrantLock lock = new ReentrantLock();
//		System.out.println(lock.isHeldByCurrentThread());
////		lock.unlock();
//		lock.tryLock();
//		lock.tryLock();
//		System.out.println(lock.isHeldByCurrentThread());
//		lock.unlock();
//		System.out.println(lock.isHeldByCurrentThread());
//		lock.unlock();
//		System.out.println(lock.isHeldByCurrentThread());
		
//		ArrayBlockingQueue 
//		Thread thread = new ParkCheckableThread(new Test(), 20000000000l);
//		thread.start();
//		tPrint("park thread "+thread);
//		sleep(2000l);
//		LockSupport.unpark(thread);
//		tPrint("after unpark thread "+thread);
		Sync sync = new Sync();
		Condition condition = sync.newCondition();
		tPrint("创建contidon="+condition);
		new TimeoutAwaitOnCondition(sync, condition, 5000l).start();
		Thread.sleep(2000l);
		tPrint("signal contidon="+condition);
		condition.signal();
	}

	
	private static void test(Collection<Number> list) {
		
	}
	
//	private static void test(Collection<String> list) {
//		
//	}
	
	
//	static class Exception<T> extends RuntimeException {
//		
//	}
	
	static class ParkCheckableThread extends Thread {
		private long t;
		Object monitor;
		public ParkCheckableThread(Object monitor, long t) {
			this.t = t;
			this.monitor = monitor;
		}
		
		public void run() {
			if(t>0) {
				long deadline = System.nanoTime() + t;
				for(;;) {
					t = deadline - System.nanoTime();
					if(t <=0 ) {
						System.out.println("time is up for park");
						break;
					}
					LockSupport.parkNanos(monitor, t); 
					System.out.println("wake up from park, last sleep time="+t);
				}
			} else {
				tPrint("is parked");
				LockSupport.park(monitor);
				tPrint("is waked up from park");
			}
		}
		
	} 
	
	static class TimeoutAwaitOnCondition extends Thread {
		private long t;
		Object monitor;
		Condition condition;
		public TimeoutAwaitOnCondition(Object monitor, Condition condition, long t) {
			this.t = t;
			this.monitor = monitor;
			this.condition = condition;
		}
		
		public void run() {
			try {
				if(t > 0) {
					tPrint("tWait condition="+condition+", t="+t);
					condition.await(t, TimeUnit.MICROSECONDS);
				} else {
					tPrint("wait condition="+condition);
					condition.await();
				}
				tPrint("receive signal from condition="+condition);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()+ " is interrupted");
			}
		}
	}
	
	static class Sync extends AbstractQueuedSynchronizer {
		protected boolean tryAcquire(int arg) {
	        return true;
	    }
		protected boolean tryRelease(int arg) {
	        return true;
	    }
		protected final boolean isHeldExclusively() {
            return true;
        }
		public Condition newCondition() {
			return new ConditionObject();
		}
	} 
}
