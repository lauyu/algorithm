package com.test;

public class ThreadJoinTest extends BaseTester {

	public static void main(String[] args) throws Exception {
		JoinThread thread = new JoinThread(3000);
		thread.start();
		thread.join(1000);
		
		System.out.println("main end...");
	}

	
	static class JoinThread extends Thread {
		int t;
		public JoinThread(int t) {
			this.t = t;
		}

		@Override
		public void run() {
			printThread(Thread.currentThread());
			try {
				Thread.sleep(t);
			} catch (Exception e) {
			}
			System.out.println(Thread.currentThread()+" end...");
		}
	}
}
