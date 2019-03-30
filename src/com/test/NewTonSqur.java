package com.test;

public class NewTonSqur {

	private static double squr(double a) {
		double x1 = a;
		double err = 1e-7;
//		double x2=Double.NEGATIVE_INFINITY;
		while ((Math.abs(x1*x1-a)) > err) {
			x1 = (a/x1+x1)/2.0;
		}
		return x1;
	}
	
	public static void main(String[] args) {
		System.out.println(squr(2));
	}

}
