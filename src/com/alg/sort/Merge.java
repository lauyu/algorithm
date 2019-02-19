package com.alg.sort;

import java.util.Arrays;

import com.alg.util.RandomUtil;

public class Merge extends AbstractSort {
	
	private int[] aux;
	
	/**
	 * 升序排序
	 * @param array
	 */
	public void sort(int[] array) {
		aux = new int[array.length];
		sort(array, 0, array.length-1);
	}
	
	private void sort(int[] array, int l, int h) {
		if(l>=h) return;
		System.out.println("l="+l+", h="+h);
		int mid = (l+h)/2;
		sort(array, l, mid);
		sort(array, mid+1, h);
//		merge(array, l, h, mid);
		merge2(array, l, h, mid);
	}
	
	/**
	 * 合并已排序的数组的两个区间[l, m]、[m+1, r]，如果m=l，可以看做区间只有一个元素的子数组，就是有序的。
	 * @param array
	 * @param l
	 * @param r
	 * @param m
	 */
	private void merge(int[] array, int l, int r, int m) {
		for(int i=l;i<=r;i++)
			aux[i] = array[i]; //这里可能会有并发性问题
		
		int i=l,j=m+1;
		for(int k=l;k<=r;k++) {
			if(i>m) { //首先处理区间边界
				array[k] = aux[j++];
			} else if (j>r) { //首先处理区间边界
				array[k] = aux[i++];
			} else if (isLess(aux[j], aux[i])) { //因为是升序，所有这里要求aux[j]必须要大于aux[i]
				array[k] = aux[j++];
			} else {
				array[k] = aux[i++];
			}
		}
	}
	
	/**
	 * 优化merge，解决并发问题和节省协助数组的空间
	 * @param array
	 * @param l
	 * @param r
	 * @param m
	 */
	private void merge2(int[] array, int l, int r, int m) {
		int[] aux = new int[r-l+1];
		for(int i=l;i<=r;i++)
			aux[i-l] = array[i]; 
		
		int i=l,j=m+1;
		for(int k=l;k<=r;k++) {
			if(i>m) { //首先处理区间边界
				array[k] = aux[j-l];
				j++;
			} else if (j>r) { //首先处理区间边界
				array[k] = aux[i-l];
				i++;
			} else if (isLess(aux[j-l], aux[i-l])) { //因为是升序，所有这里要求aux[j]必须要大于aux[i]
				array[k] = aux[j-l];
				j++;
			} else {
				array[k] = aux[i-l];
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i=0;i<arr.length;i++) {
			arr[i] = RandomUtil.getInt(10);
		}
		print(arr);
		System.out.println();
		
		Merge sort = new Merge();
		sort.sort(arr);
		print(arr);
		alertSorted(arr);
		print(arr);
	}

}
