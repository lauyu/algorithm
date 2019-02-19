package com.test;

import java.lang.reflect.Method;

public class ReflectTest {

	private String priField = "private filed of string in ReflectTest";
	
	String field = "filed of string in ReflectTest";
	
	public String pubField = "public filed of string in ReflectTest";
	
	private void privateMethod() {
		
	}
	
	public void method1(int arg) {
		System.out.println("this="+this+" call method1 with args="+arg);
	}
	
	public void method2(int arg1) {
	}
	
	public void method3(int arg1) {
	}
	
	//扰乱内联优化的方法
	private static void polluteProfile() throws Exception {
		Method method1 = ReflectTest.class.getMethod("method2", int.class);
		Method method2 = ReflectTest.class.getMethod("method3", int.class);
		ReflectTest obj = new ReflectTest();
		for(int i=0;i<2000;i++) {
			method1.invoke(obj, 1);
			method1.invoke(obj, 2);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Method method = ReflectTest.class.getMethod("method1", int.class);
		polluteProfile();
		ReflectTest obj = new ReflectTest();
		long cur = System.currentTimeMillis();
		for(int i=0;i<2000000000;i++) {
			if(i % 100000000 == 0) {
				long tmp = System.currentTimeMillis();
				System.out.println("cost=" + (tmp - cur));
				cur = tmp;
			}
			method.invoke(obj, 128);	
		}
	}

}
