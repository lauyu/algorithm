package com.alg.array;

import com.alg.sort.AbstractSort;
import com.alg.sort.Merge;
import com.alg.util.RandomUtil;

/**
 * 求数组逆数对，即i>j，arr[i]<arr[j]。算法思路：利用分组排序的思想，每一次对数组进行对半分组，由小到大分区合并分组，因为每个
 * @author LIUYU20
 *
 */
public class InverseNumberPair extends AbstractSort {
	private int[] aux;
	private int pairs;
	/**
	 * 升序排序
	 * @param array
	 */
	public void count(int[] array) {
		pairs = 0;
		aux = new int[array.length];
		sort(array, 0, array.length-1);
	}
	
	private void sort(int[] array, int l, int h) {
		if(l>=h) return;
		System.out.println("l="+l+", h="+h);
		int mid = (l+h)/2;
		sort(array, l, mid);
		sort(array, mid+1, h);
		merge(array, l, h, mid);
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
//				System.out.println("l="+l+", r="+r+", m="+m);
				for(int x=i;x<=m;x++) {
					System.out.println(j + "," + i);
				}
				pairs += m-i+1;
				array[k] = aux[j++];
			} else {
				array[k] = aux[i++];
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
		
		InverseNumberPair sort = new InverseNumberPair();
		sort.count(arr);
		print(arr);
		alertSorted(arr);
		print(arr);
		System.out.println("pairs = "+sort.pairs);
	}

}
