package com.alg.array;

import java.util.Arrays;

import com.alg.sort.AbstractSort;

public class FindMissNum extends AbstractSort {

	public int find(int[] arr) {
		int size = arr.length;
		int i=1, end=size;
		while (i<=end) {
			int cur = arr[i-1];
			System.out.println("i="+i+", a[i]="+cur+", end="+end);
			if(cur == i) {
				i++;
			} else if (cur < i || cur>end || cur==arr[cur-1]) {
				arr[i-1] = arr[end-1];
				end--;
			} else { //i<arr[i-1]<end
				swap(arr, i-1, cur-1);
			}
		}
//		if(i<arr.length) return ;
		return i;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {-1,0,1,19,5,3,2,4,8,9,7,6};
		System.out.println(Arrays.toString(arr));
		FindMissNum missNum = new FindMissNum();
		System.out.println(missNum.find(arr));
	}

}
