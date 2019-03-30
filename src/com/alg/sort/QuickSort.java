package com.alg.sort;

import com.alg.util.RandomUtil;

public class QuickSort extends AbstractSort {

	
	public void sort(int[] arr, int low, int high) {
		System.out.println(String.format("low=%d, high=%d", low, high));
		if(low >= high) return;
		int m = partion(arr, low, high);
//		int m = partion1(arr, low, high);
		sort(arr, low, m-1);
		sort(arr, m+1, high);
	}
	
	private int partion(int[] arr, int low, int high) {
		int v = arr[low];
		int i=low, j=high+1;
		
		while(true) {
			while (arr[++i] <= v) {
				if(i>=high) break;
			}
			while (arr[--j] >= v) {
				if(j<=low) break;
			}
			System.out.println(String.format("i=%d, j=%d, v=%d", i, j, v));
			if(i>=j) break;
			swap(arr, i, j);
		}
		swap(arr, low, j);
		return j;
	}
	
	private int partion1(int[] arr, int low, int high) {
		int k = arr[low];
		int i=low+1, j=high;
//		System.out.println(String.format("low=%d, high=%d, k=%d", low, high, k));
		while (i<j) {
			while (arr[i] < k) {
				i++;
				if(i>=j) break;
			}
			while (arr[j] >= k) {
				j--;
				if(i>=j) break;
			}
			System.out.println(String.format("i=%d, j=%d, k=%d", i, j, k));
			if(i>=j) break;
			swap(arr, i, j);
		}
		swap(arr, low, i-1);
		return j;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[100];
		for(int i=0;i<arr.length;i++) {
			arr[i] = RandomUtil.getInt(100);
		}
		print(arr);
		System.out.println();
		QuickSort sort = new QuickSort();
		sort.sort(arr, 0, arr.length-1);
		print(arr);
		alertSorted(arr);
	}
}
