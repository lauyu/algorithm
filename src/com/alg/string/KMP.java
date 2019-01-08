package com.alg.string;

import java.util.Arrays;

import sun.net.www.content.text.plain;

public class KMP {

	/**
	 * 暴力求解next数组
	 * @param p
	 * @return
	 */
	private int[] nextByForce(char[] p) {
		int[] next = new int[p.length];
		next[0] = -1;
		int k=0;
		int j=1;
		int n=0;
		while (j<p.length) {
			k=j-1;
			int max=0;
			for(;n<k;n++) {
				int i=0; 
				while (i<=n) {
					if(p[i]!=p[j-n+i-1]) {
						i=0;
						break;
					}
					i++;
				}
				if(max < i)
					max = i;
			}
			next[j] = max;
			n=0;
			j++;
		}
		return next;
	}
	
	@Deprecated
	private int[] next(char[] p) {
		int[] next = new int[p.length];
		next[0] = -1;
		int k=next[0];
		int j=1;
		while (j<p.length) {
			if(k==-1 || p[k]==p[j]) {
				next[j++] = ++k;
			} else {
				k=next[j-1];	
			}
		}
		return next;
	}
	
	private int[] getNext(char[] p) {
		int[] next = new int[p.length];
		next[0] = -1;
		int k=-1;
		int j=0;
		while (j<p.length-1) {
			if(k==-1 // k=-1表示根据历史的next[k]查询不到j+1的next数组的值， 
				|| p[k]==p[j]) {
				k++;
				j++;
				next[j] = k;
			} else {
				k = next[k];
			}
		}
		return next;
	}
	
	public int kmp(String s, String p) {
		char[] sArr = s.toCharArray();
		char[] pArr = p.toCharArray();
		int[] next = getNext(pArr);
		int i=0, j=0;
		while (i<sArr.length) {
			if(j==-1 || sArr[i]==pArr[j]) {
				if(j==pArr.length-1) return i-j;
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		KMP kmp = new KMP();
		String pattern = "abaabcaba";
//		for(int i: kmp.next(pattern.toCharArray())) {
//			System.out.print(i+",");
//		}
		
//		for(int i: kmp.getNext(pattern.toCharArray())) {
//			System.out.print(i+",");
//		}
//		System.out.println();
//		for(int i: kmp.next(pattern.toCharArray())) {
//			System.out.print(i+",");
//		}
		String string = "123456789abaabcaba";
		System.out.println(kmp.kmp(string, pattern));
	}

}
