package com.alg.string;

public class Manacher {

	public int[] manacher(String s) {
		StringBuilder builder = new StringBuilder();
		char[] charArr = s.toCharArray();
		for(int i=0;i<charArr.length;i++) 
			builder.append("#").append(charArr[i]);
		builder.append("#");
		System.out.println(builder.toString());
		charArr = builder.toString().toCharArray();
		int[] p = new int[charArr.length];
		p[0] = 1;
		int j = 1;
		int id = 0;//最大回文串的中心字符的位置
		for(int i=1;i<charArr.length;i++) {
			if(p[id] + id > i) {
				j = Math.min(p[id*2 - i], p[id] + id -i); //
			} else {
				j = 1;
			}
			for(;j<=i && (i+j)<charArr.length && charArr[i-j]==charArr[i+j];j++);
			System.out.println("i="+i+", j="+j);
			p[i] = j;
			if(p[id] < j) {
				id = i;
			}
			j=1;
		}
		return p;
	}
	
	public static void main(String[] args) {
		Manacher manacher = new Manacher(); 
		for(int i: manacher.manacher("12212321"))
			System.out.print(i+" ");
	}

}
