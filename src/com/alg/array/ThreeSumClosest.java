package com.alg.array;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, 
 * find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * Example:
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * @author LIUYU20
 *
 */
public class ThreeSumClosest {

	public int threeSumClosest(int[] nums, int target) {
		int size = nums.length;
        int difference = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        for(int i=0;i<size-2;i++) {
        	for(int j=i+1;j<size-1;j++) {
        		for(int k=j+1;k<size;k++) {
        			System.out.println(i+" "+j+" "+k);
        			int sum = nums[i] + nums[j] +nums[k];
                	if(Math.abs(sum-target) < Math.abs(difference)) {
                		difference = sum-target;
                		result = sum;
                	} else if (sum == target) {
						return sum;
					}
            	}
        	}
        }
        return result;
    }
	
	/**
	 * 先对数组进行排序
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest1(int[] nums, int target) {
		int size = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int result = nums[0] + nums[1] +nums[2];
        Arrays.sort(nums);
        for(int i=0;i<size-2;i++) {
        	int start=i+1, end=size-1;
        	while (start<end) {
        		int sum = nums[i] + nums[start] +nums[end];
        		int diff = sum-target;
        		if(Math.abs(sum-target) < Math.abs(minDiff)) {
        			minDiff = sum-target;
            		result = sum;
            		if(diff == 0) return sum;
        		}
        		if(diff > 0) end--;
        		else start++;
			}
        }
        return result;
    }
	
	/**
	 * 相比{@link #threeSumClosest1}, 把循环变量的后缀++变为前缀++
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest2(int[] nums, int target) {
		 int size = nums.length;
	        int minDiff = Integer.MAX_VALUE;
	        int result = nums[0] + nums[1] +nums[2];
	        Arrays.sort(nums);
	        for(int i=0;i<size-2;++i) {
	        	int start=i+1, end=size-1;
	        	while (start<end) {
	        		int sum = nums[i] + nums[start] +nums[end];
	        		int diff = sum-target;
	        		if(Math.abs(diff) < minDiff) {
	        			minDiff = Math.abs(diff);
	            		result = sum;
	            		if(diff == 0) return sum;
	        		}
	        		if(diff > 0) --end;
	        		else ++start;
				}
	        }
	        return result;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {-1, 2, 1, -4};
		System.out.println(new ThreeSumClosest().threeSumClosest(nums, 1));
	}

}
