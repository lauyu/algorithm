package com.alg.array;

import java.util.Arrays;

import com.alg.util.PrintUtil;
import com.alg.util.RandomUtil;

public class Holland {

	int[] array;
	
	public Holland(int[] array) {
		this.array = array;
	}
	
	/**
	 * [0, begin)存放的是0
	 * [begin, cur)存放的1
	 * (end, array.length-1]存放的是2
	 * [cur, end]范围是未知
	 */
	public void arrange() {
		int begin=0, cur=0, end=array.length-1;
		while(cur<=end) {
			if(array[cur] == 2) {
				swap(cur, end--);
			} else if (array[cur] == 1) {
				cur++;
			} else {
				if(begin == cur) {
					begin++;
					cur++;
				} else {
					swap(begin++, cur++);
				}
			}
		}
	}
	
	public void arrange1() {
		int begin=0, cur=0, end=array.length-1;
		while(cur<=end) {
			if(array[cur] == 2) {
				swap(cur, end--);
			} else if (array[cur] == 1) {
				cur++;
			} else {
				if(begin != cur) {
					swap(begin, cur);
				}
				begin++;
				cur++;
			}
		}
	}
	
	private void swap(int form, int to) {
		int c = array[form];
		array[form] = array[to];
		array[to] = c;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i=0;i<arr.length;i++) {
			arr[i] = RandomUtil.getInt(3);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println();
		
		Holland holland = new Holland(arr);
		holland.arrange1();
		System.out.println(Arrays.toString(holland.array));
	}
}
