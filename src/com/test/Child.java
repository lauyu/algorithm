package com.test;

public class Child extends Parent {

	int iField_c=111;
	long lField_c= Long.valueOf("0110000000", 16);
	int iField_c_2=222;
	
	public Child() {
		System.out.println("iField_c="+iField_c);
		System.out.println("lField_c="+lField_c);
		System.out.println("iField_c_2="+iField_c_2);
	}
	
	@Override
	public int hashCode() {
		return 0x01010101;
	}
	
	public static void main(String[] args) {
		Child child = new Child();
		System.out.println(child.getiField_f());
		child.setiField_f(123);
		System.out.println(child.getiField_f());
	}

}
