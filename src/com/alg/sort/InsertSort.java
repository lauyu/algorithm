package com.alg.sort;

import java.util.Arrays;

import com.alg.util.RandomUtil;

public class InsertSort extends AbstractSort {

	public void insertSort(int[] arr) {
		
		for(int i=arr.length-1;i>0;i--) {
			int max=i;
			for(int j=i-1;j>=0;j--) {
				if(arr[j]>arr[max])
					max=j;
			}
			swap(arr, max, i);
		}
	}
	
	public void insertSort2(int[] arr) {
		
		for(int i=0;i<arr.length-1;i++) {
			int min = i;
			for(int j=i+1;j<arr.length;j++) {
				if(isLess(arr[j], arr[min])) {
					min = j;
				}
			}
			swap(arr, min, i);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i=0;i<arr.length;i++) {
			arr[i] = RandomUtil.getInt(10);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println();
		
		InsertSort sort = new InsertSort();
//		sort.insertSort(arr);
		sort.insertSort2(arr);
		alertSorted(arr);
		System.out.println(Arrays.toString(arr));
	}

}
