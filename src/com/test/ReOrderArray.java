package com.test;

import java.util.Arrays;

import com.alg.util.PrintUtil;

public class ReOrderArray extends BaseTester {

	public void reOrderArray(int [] array) {
        java.util.List<Integer> odd = new java.util.LinkedList();
        java.util.List<Integer> even = new java.util.LinkedList();
        int h=0, t=array.length-1;
        int i=0, j=0;
        while(h<t) {
            i = even.size();
            j = odd.size();
            System.out.println("i="+i+", j="+j);
            if(array[h]%2 == 1) {
                if(i>0) {
                    array[h-i]=array[h];
                }                
            } else {
                even.add(array[h]);
            }           
            if(array[t]%2 == 0) {
                if(j>0) {
                    array[t+j]=array[t];
                }                
            } else {
                odd.add(array[t]);
            }
            h++;
            t--;
        }
        i = even.size();
        j = odd.size();
        if(h==t) {
            if(array[h]%2 == 1) {                
                if(i>0) {
                    array[h-i]=array[h];
                }                
            } else {
                even.add(array[h]);
            }  
            t++;
        } else if(h > t) {
            h--;
            t++;
        }
        
        h=h-odd.size()+1;
        i = odd.size()-1;
        while(i >= 0) {
            array[h++] = odd.get(i--);
        }
        j = 0;
        while(j < even.size()) {
            array[h++] = even.get(j++);
        }
    }
	
	public static void main(String[] args) {
		char[] arr = "eBdAddsa".toCharArray();
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		int[] array = {1,2,3,4,5,6,7};
		ReOrderArray tArray = new ReOrderArray();
		tArray.reOrderArray(array);
		System.out.println(Arrays.toString(array));
	}

}
