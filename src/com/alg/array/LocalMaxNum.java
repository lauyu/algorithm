package com.alg.array;

import java.util.Arrays;

import com.alg.sort.InsertSort;

public class LocalMaxNum {

	public int maxNum(int[] arr) {
		
		int begin = 0, end = arr.length-1;
		int mid=(begin+end)/2;
		while (begin < end) {
			if(arr[mid]>arr[mid+1]) {
				end = mid;
			} else {
				begin = mid+1; //必须要每次循环都要缩小查询的范围
			}
			System.out.println("begin="+begin+", end="+end+", mid="+mid);
			mid = (begin+end)/2;
		}
		return arr[begin];
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {-1,0,1,10,5,3,2,4,8,9,7,6};
		System.out.println(Arrays.toString(arr));
//		new InsertSort().insertSort(arr);
//		System.out.println(Arrays.toString(arr));
		System.out.println("maxNum= " + new LocalMaxNum().maxNum(arr));
	}

}
