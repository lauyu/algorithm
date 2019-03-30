package com.test;

import java.lang.reflect.Field;
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
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import sun.misc.Unsafe;

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
		test(null);
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
		Character character = Character.valueOf('\123');
//		character.
//		System.out.println(Integer.toBinaryString(Integer.valueOf(character.toString())));
//		System.out.println('\123');
//		ReentrantReadWriteLock
	
	}

	
	private static void test(Collection<Number> list) {
		Field f;
		try {
			f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
	        Unsafe unsafe = (Unsafe) f.get(null);

	        String[] array1 = new String[]{"abc", "efg", "hij", "kl", "mn", "xyz"};
	        String[] array2 = new String[]{"abc1", "efg1", "hij1", "kl1", "mn1", "xyz1"};
	        Class<?> ak = String[].class;
	        int ABASE = unsafe.arrayBaseOffset(ak);
	        int scale = unsafe.arrayIndexScale(ak);
	        int ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
	        String array11 = (String) unsafe.getObject(array1, ((long) 2 << ASHIFT) + ABASE);
	        String array21 = (String) unsafe.getObject(array2, ((long) 2 << ASHIFT) + ABASE);
	        System.out.println(ABASE);
	        System.out.println(scale);
	        System.out.println(ASHIFT);
	        System.out.println(array11);
	        System.out.println(array21);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
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
