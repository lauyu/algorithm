package com.alg.sort;

import java.util.Arrays;

public abstract class AbstractSort {

	protected static void alertSorted(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			if(arr[i]>arr[i+1])
				throw new RuntimeException("检测到无序：idx="+i);
		}
	}
	
	protected static void print(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}
	
	protected static void swap(int[] arr, int from, int to) {
		int s = arr[from];
		arr[from] = arr[to];
		arr[to] = s;
	}

	protected boolean isLess(int i, int j) {
		return i<j;
	}
	
	protected boolean notMoreThan(int i, int j) {
		return i<=j;
	}
}
