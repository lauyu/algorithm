package com.test;

public class MonitorLockTest {

	public synchronized static void syncFun() {
		System.out.println(Thread.currentThread()+" enter static lock");
		sleep(3000);
		System.out.println(Thread.currentThread()+" exit static lock");
	}
	

	public void syncInstFun() {
		System.out.println(Thread.currentThread()+" enter instance lock");
		sleep(3000);
		System.out.println(Thread.currentThread()+" exit instance lock");
	}
	
	public static void main(String[] args) {
		MonitorLockTest lt1 = new MonitorLockTest();
		new Thread() {
			public void run() {
				MonitorLockTest lt2 = new MonitorLockTest();
				lt1.syncFun();		
			}
		}.start();
		lt1.syncFun();
	}
	
	private static void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (Exception e) {
		}
	}

}
