package com.alg.ll;

public class ModeNumber {

	public ModeNumber() {
	}

	public static int findModeNum(int[] arr) {
		int c=0;
		int mode=arr[0];
		for(int i=0;i<arr.length;i++) {
			if(c==0) {
				mode=arr[i];
				c++;
			} else if (mode != arr[i]) {
				c--;
			} else {
				c++;
			}
		}
		
		if(count(arr, mode) > arr.length/2) return mode;
		return -1;
	}
	
	private static int count(int[] arr, int k) {
		int c=0;
		for(int i=0;i<arr.length;i++) 
			if(arr[i]==k) c++;
		return c;
	}
	
	public static int[] find(int[] arr) {
		int cm=0, cn=0;
		int m1=0, m2=0;
		for(int i=0;i<arr.length;i++) {
			if(cm==0) {
				m1=arr[i];
				cm++;
			} else if (m1==arr[i]) {
				cm++;
			} else if(cn==0) {
				m2=arr[i];
				cn++;
			} else if (m2==arr[i]) {
				cn++;
			} else {
				cm--;
				cn--;
			}
		}
		System.out.println("cm="+cm+", m1="+m1+", cn="+cn+", m2="+m2);
		if(count(arr, m1)>arr.length/3)
			System.out.println("1/3 mode num= "+m1);
		if(count(arr, m2)>arr.length/3)
			System.out.println("1/3 mode num= "+m2);
		return new int[1];
	}
	
	public static void main(String[] args) {
//		int[] arr = new int[10];
		int[] arr = new int[] {3,3,3,1,2,1,2};
//		for()
//		System.out.println(new ModeNumber().findModeNum(arr));
		new ModeNumber().find(arr);
		
	}

}
