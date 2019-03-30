package com.test;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class AbstractInspactor extends BaseTester {
	static Unsafe U;
	static {
		try {
	        Field f = Unsafe.class.getDeclaredField("theUnsafe");
	        f.setAccessible(true);
	        U = (Unsafe)f.get(null);
		} catch (Exception e) { 
		    /* ... */
		}	
	}
	
	protected static String toBinary(int i) {
		return Integer.toBinaryString(i);
	}

	private static String toBinary(long i) {
		return Long.toBinaryString(i);
	}

	public AbstractInspactor() {
		super();
	}

	protected static long printOffset(Field field) {
		long offset = U.objectFieldOffset(field);
		System.out.println("offset "+ field.getName()+"=" + (offset));
		return offset;
	}

	protected static void printInstanceHead(Object obj) {
		System.out.println(Thread.currentThread().getName() + ", mw:0=" +toBinary(U.getInt(obj, 0)));
		System.out.println(Thread.currentThread().getName() + ", mw:4=" +toBinary(U.getInt(obj, 4)));
		System.out.println(Thread.currentThread().getName() + ", mw:8=" +toBinary(U.getInt(obj, 8)));
		System.out.println();
	}

}