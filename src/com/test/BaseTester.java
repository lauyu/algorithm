package com.test;

public class BaseTester {

	protected static void printThread(Thread t) {
		System.out.println("Thread: t="+t+", id="+t.getId());
	}

	protected static void tPrint(String msg) {
		System.out.println(Thread.currentThread()+", msg="+msg);
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