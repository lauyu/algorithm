package com.alg.array;

import java.util.Arrays;

import com.alg.sort.InsertSort;

public class SubArrayWithZeroSum {

	InsertSort sort = new InsertSort();
	
	/**
	 * 1.s=array[arr.length+1], s[i]为以i结尾的子序列的和，1<i<=arr.length, s[-1]=0;
	 * 2.对s进行排序
	 * 3.arr[i]+..+a[j] = s[j] - s[i]
	 * @param arr
	 * @return
	 */
	public int zeroSumSubArr(int[] arr) {
		int[] s = new int[arr.length + 1];
		s[0] = 0;
		for(int i=1; i<=arr.length; i++) {
			s[i] = s[i-1] + arr[i-1];
		}
		sort.insertSort(s);
		int min = Integer.MAX_VALUE;
		for(int i=1;i<s.length;i++) {
			if((s[i]-s[i-1]) < min) {
				min = s[i]-s[i-1];
			}
		}
		return min;
	}
	
	public int printZeroSumSubArr(int[] arr) {
		int[] s = new int[arr.length + 1];
		s[0] = 0;
		for(int i=1; i<=arr.length; i++) {
			s[i] = s[i-1] + arr[i-1];
		}
		System.out.println(Arrays.toString(s));
		sort.insertSort(s);
		System.out.println(Arrays.toString(s));
		int min = Integer.MAX_VALUE;
//		int start = 0;
//		int end = 0;
		for(int i=1;i<s.length;i++) {
			if((s[i]-s[i-1]) < min) {
				System.out.println("i="+i + ", s[i]="+s[i]+", s[i-1]="+s[i-1]);
				min = s[i]-s[i-1];
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
		System.out.println(Arrays.toString(arr));
		SubArrayWithZeroSum zeroSum = new SubArrayWithZeroSum();
		System.out.println(zeroSum.printZeroSumSubArr(arr));;
	}

}
