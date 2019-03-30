package com.alg.dp;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import com.alg.util.RandomUtil;

public class LIS1{

	public static int lis(int[] a) {
		int[] b = new int[a.length];
		b[0] = 1;
		int i=1;
		int m=1;
		Queue<Integer> trace = new LinkedList<>(); 
		while (i<a.length) {
			int k=1;
			int l = a[i]; 
			for(int j=0;j<i;j++) {
				if(a[j] <= l && b[j]>k)
					k=b[j];
			}
//			System.out.println("i="+i+",k="+k+", b[i-1]="+b[k]);
			b[i] = k+1;
			if(b[i] > m)
				m = b[i];
			i++;
		}
		System.out.println(Arrays.toString(b));
		return m;
	}
	
	/**
	 * 可以追踪最长递增子序列的
	 * @param a
	 * @return
	 */
	public static int lis(int[] a, int[] pre) {
		int[] b = new int[a.length];
		b[0] = 1;
		for(int i=0;i<pre.length;i++)
			pre[i] = -1;
		int i=1;
		int m=1;
		while (i<a.length) {
			int k=0;
			int l = a[i]; 
			for(int j=0;j<i;j++) {
				if(a[j] <= l && b[j]>k) {
					k=b[j];
					pre[i] = j;
//					System.out.println("i="+i+" , k="+k+" , j="+j);
				}
			}
			b[i] = k+1;
			if(b[i] > m)
				m = b[i];
			i++;
		}
		return m;
	}
	
	private static void getLIS(int[] a, int[] pre, int i) {
		StringBuilder lis = new StringBuilder();
		int cur=i;
		Deque<Integer> trace = new LinkedList<>();		
		while (cur>=0) {
			trace.push(a[cur]);
			cur = pre[cur];
		}
		while (trace.peekFirst() != null) {
			System.out.print(trace.pop());
			System.out.print(" -> ");
		}
	}
	
	public static void main(String[] args) {
//		int[] a = {1, 4, 6, 2, 8, 9, 7};
		int[] a = new int[10];
		for(int i=0;i<a.length;i++) {
			a[i] = RandomUtil.getInt(10);
		}
		System.out.println(Arrays.toString(a));
		int[] pre = new int[a.length];
//		System.out.println(lis(a));
		lis(a, pre);
		System.out.println(Arrays.toString(pre));
		getLIS(a, pre, a.length-1);
	}

}
