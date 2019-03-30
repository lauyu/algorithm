package com.test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class OOMTest extends BaseTester {

	private static final int KB = 1024;
	private static final int MB = KB*1024;
	
	public static void main(String[] args) {
		byte[] arr1 = new byte[2*MB];
		System.out.println("after 2MB byte[]");
		tSleep(1000);
		
//		int[] arr2 = new int[MB];
//		System.out.println("after 1*MB int[]");
//		tSleep(1000);
//		
//		
//		int[] arr3 = new int[1*MB];
//		System.out.println("after 1*MB int[]");
//		tSleep(1000);
		ConcurrentLinkedQueue<E>
		byte[] arr4 = new byte[100*KB];
		System.out.println("after 100*k byte[]");
		tSleep(1000);
		
		byte[] arr5 = new byte[10*KB];
		System.out.println("after 10*k byte[]");
		tSleep(1000);
		
		
		byte[] arr6 = new byte[800*KB];
		System.out.println("after 800*k byte[]");
		tSleep(1000);
		
		byte[] arr7 = new byte[5*MB];
		System.out.println("after 5MB byte[]");
	}

}
