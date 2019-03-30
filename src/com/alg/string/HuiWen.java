package com.alg.string;

public class HuiWen {

	public static boolean isHuiWen(String s) {
		int i=0, j=s.length()-1;
		char[] arr = s.toCharArray();
		while (i<j) {
			if(arr[i++] != arr[j--])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(isHuiWen(""));
		System.out.println(isHuiWen("121"));
		System.out.println(isHuiWen("122321"));
	}

}
