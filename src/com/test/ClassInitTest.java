package com.test;

public class ClassInitTest {
	static final String sFinal2 = "static final string222";
	static final String s2 = "static string222";
	static int i;
	static String s;
	static final int iFinal;
	static final String sFinal;
	static {
		System.out.println(i);
		System.out.println(s);
		System.out.println(sFinal2);
		System.out.println(s2);
//		System.out.println(iFinal);
//		System.out.println(sFinal);
		iFinal=100;
		sFinal="static final string";
	}
	
	public static void main(String[] args) {
		System.out.println("exe  main ");
	}

}
