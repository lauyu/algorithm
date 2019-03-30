package com.test;

public class LifeTimeTest {
	private static final int K = 1024;
	private static final int M = K * K;
	private static final int G = K * M;

	private static final int ALIVE_OBJECT_SIZE = 32 * M;
//	private static final int ALIVE_OBJECT_SIZE = 12800 * K;
	
	public static void main(String[] args) {
		int length = ALIVE_OBJECT_SIZE / 64;
//		int length = K * K / 64;
		ObjectOf64Bytes[] array = new ObjectOf64Bytes[length];
//		for (long i = 0; i < G; i++) {
//			if(i % 100000 ==0)
//			System.out.println("allocate i="+i);
//			array[(int) (i % length)] = new ObjectOf64Bytes();
//		}
		new Thread() {
			public void run() {
				sync();
			}
		}.start();
		sync();
	}
	
	public static synchronized void sync() {
		try {
			System.out.println(Thread.currentThread() +" call sync...");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread() +" exit sync...");
		}
	}
}


class ObjectOf64Bytes {
	long placeholder0;
	long placeholder1;
	long placeholder2;
	long placeholder3;
	long placeholder4;
	long placeholder5;
}