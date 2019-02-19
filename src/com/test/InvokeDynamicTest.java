package com.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class InvokeDynamicTest {

	public static void staticMethod(int arg) {
		System.out.println("call staticMethod with arg="+arg);
	}
	
	public void method(int arg) {
		System.out.println("call method with arg="+arg);
	}
	
	public static Lookup lookup() {
		return MethodHandles.lookup();
	}
	public static void main(String[] args) throws Throwable {
		Lookup lookup = lookup();
		MethodType intMT = MethodType.methodType(void.class, int.class);
		MethodType strMT = MethodType.methodType(void.class, String.class);
		MethodType voidMT = MethodType.methodType(void.class);
//		lookup.findSpecial(ReflectTest.class, "method1", methodType, ReflectTest.class);
//		lookup.unreflect(m);
		
		MethodHandle mh1 = lookup.findVirtual(ReflectTest.class, "method1", intMT);
		ReflectTest obj = new ReflectTest();
		System.out.println(obj);
		mh1.invokeExact(obj, 123);
		MethodHandle fieldMH = lookup.findGetter(ReflectTest.class, "pubField", String.class);
		System.out.println(fieldMH.invoke(obj));
		MethodHandle fieldSMH = lookup.findSetter(ReflectTest.class, "pubField", String.class);
		fieldSMH.invoke(obj, "public field setted from mh");
		System.out.println(fieldMH.invoke(obj));
//		MethodHandle mh2 = lookup.findVirtual(ReflectTest.class, "method1", strMT);
//		mh2.invoke(`)
	}

}
