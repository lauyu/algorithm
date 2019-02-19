package com.alg.array;

import java.util.Arrays;

import com.alg.util.RandomUtil;

public class SubArrayWithMaxSum {

	private static int maxSum(int[] arr) {
		int[] s=new int[arr.length];
		s[0]=arr[0];
		int max=arr[0];
		for(int j=1;j<arr.length;j++) {
			if(s[j-1] >= 0) {
				s[j] = s[j-1] + arr[j];
			} else {
				s[j] = arr[j];
			}
			max = Math.max(s[j], max);
		}
		return max;
	}
	
	private static int maxSumAndPrint(int[] arr) {
		int[] s=new int[arr.length];
		s[0]=arr[0];
		int max=arr[0];
		int start=0;
		int end=0;
		for(int j=1;j<arr.length;j++) {
			if(s[j-1] > 0) {
				s[j] = s[j-1] + arr[j];
			} else {
				s[j] = arr[j];
				start = j;
			}
			if(s[j] > max) {
				end = j;
				max = s[j];
			}
		}
		System.out.println("最大连续子数组：");
		for(int i=start;i<=end;i++)
			System.out.print(arr[i]+", ");
		System.out.println();
		return max;
	}
	
//	private static int maxSum1(int[] arr) {
//		int[] s=new int[arr.length];
//		int max=Integer.MIN_VALUE;
//		int localSum=arr[0];
//		for(int j=0;j<arr.length;j++) {
//			localSum += arr[j];
//			if(localSum > max) {
//				max = localSum;
//			} else if (max < arr[j]) {
//				localSum = arr[j];
//			}
//		}
//		return max;
//	}
	
	public static void main(String[] args) {
//		int[] arr = new int[10];
		int[] arr = new int[] {1, -2, 3, 10, -4, 7, 2, -5};
//		for(int i=0;i<arr.length;i++) {
//			arr[i] = RandomUtil.getInt(10) - 5;
//		}
		System.out.println(Arrays.toString(arr));
		System.out.println();
		System.out.println(SubArrayWithMaxSum.maxSum(arr));
		System.out.println(SubArrayWithMaxSum.maxSumAndPrint(arr));
	}

}
