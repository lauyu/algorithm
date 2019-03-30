package com.test;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import sun.misc.Unsafe;

public class UnsafeTest extends AbstractInspactor {
    Class<?> k = Child.class;
    
	public static void main(String[] args) throws Exception {
		UnsafeTest test = new UnsafeTest();
		Child child = new Child();
		test.test(child);
	}

	private void test(Child child) throws Exception {
//		System.out.println(child.getiField_f());
		System.out.println(Long.toBinaryString(child.lField_c));
//		printOffset(k.getDeclaredField("iField_c"));
//		printOffset(k.getDeclaredField("iField_c_2"));
//		printOffset(k.getDeclaredField("lField_c"));
//		
//		printOffset(UnsafeTest.class.getDeclaredField("U"));
//		printOffset(UnsafeTest.class.getDeclaredField("k"));
//		System.out.println(toBinary(U.getInt(this, 16)));
		
		printInstanceHead(child);
		System.out.println("after hashCode");
		System.out.println(toBinary(child.hashCode()));
		printInstanceHead(child);

		System.out.println("after systemHashCode");
		System.out.println(toBinary(System.identityHashCode(child)));
		printInstanceHead(child);
		
		CountDownLatch latch = new CountDownLatch(2);
		new Thread(new Runnable() {
			public void run() {
				latch.countDown();
				printThread(Thread.currentThread());
				printInstanceHead(child);
				synchronized (child) {
					System.out.println(Thread.currentThread()+" enter monitor");
					while (true) {
						
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				latch.countDown();
				printThread(Thread.currentThread());
				printInstanceHead(child);
				synchronized (child) {
					System.out.println(Thread.currentThread()+" enter monitor");
					while (true) {
						
					}
				}
			}
		}).start();
		latch.await();
		printInstanceHead(child);
//		System.out.println(U.getInt(child, 12));
//		System.out.println(U.getInt(child, 16));
//		
//		System.out.println(U.getInt(child, 32));
//		System.out.println(U.getInt(child, 36));
//		System.out.println(toBinary(U.getInt(child, 28)));
//		System.out.println(Integer.toBinaryString(U.getInt(child, 24)));
	}
}
