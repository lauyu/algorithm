package com.test;

import java.lang.reflect.Field;

import com.sun.swing.internal.plaf.synth.resources.synth;

import sun.misc.Unsafe;

public class ObjectMemoryInspector extends AbstractInspactor {
	
	private static volatile int state = 0;
	
	public static void main(String[] args) {
		ObjectMemoryInspector inspector = new ObjectMemoryInspector();
		SimpleClass sc = new SimpleClass();
//		System.out.println(inspector.U.getInt(sc, 0));
//		System.out.println(inspector.U.getInt(sc, 4));
//		System.out.println(inspector.U.getInt(sc, 8));
//		System.out.println(inspector.U.getShort(sc, 12));
//		System.out.println(inspector.U.getShort(sc, 14));
		printInstanceHead(sc);
		sc.hashCode();
		System.out.println("after hashCode");
		printInstanceHead(sc);
		new SyncLock(sc, 1).start();
		new SyncLock(sc, 2).start();
		new SyncLock(sc, 3).start();
		tSleep(1000);
		state = 1;
		tSleep(1000);
		state = 2;
		tSleep(1000);
		state = 3;
	}
	
	static class SimpleClass {
//		private int i=0x11;
		private short s=0x11;
	}
	
	private void name() {
		
	}
	
	static class SyncLock extends Thread {
		Object obj;
		String tName;
		int on = -1;
		public SyncLock(Object obj, int rf) {
			this.obj = obj;
			this.on = rf;
		}
		public SyncLock(Object obj, String tName) {
			this.obj = obj;
			this.tName = tName;
		}
		@Override
		public void run() {
			if(tName != null)
				Thread.currentThread().setName(tName);
			while (true) {
				if(state == on) break;
			}
			printThread(Thread.currentThread());
			printInstanceHead(obj);
			synchronized (obj) {
				System.out.println(Thread.currentThread() + " enter monitor:"+obj);
				tSleep(10000);
			}
		}
	}
}
