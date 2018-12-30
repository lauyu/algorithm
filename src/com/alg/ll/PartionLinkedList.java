package com.alg.ll;

import com.alg.util.PrintUtil;
import com.alg.util.RandomUtil;

public class PartionLinkedList extends SinglyLinkedList<Integer>{
	private int partion = -1;
	public PartionLinkedList(int partion) {
		this.partion = partion;
	}

	public PartionLinkedList partion(int i) {
		Node<Integer> min = null;
		Node<Integer> max = null;
		Node<Integer> minL = null;
		Node<Integer> maxL = null;
		Node<Integer> cur = SENTINAL.next;
		while (cur != null) {
			if(cur.value < i) {
				if(min==null) {
					min = cur;
				} else {
					minL.next = cur;
				}
				minL = cur;
			} else {
				if(max==null) {
					max = cur;
				} else {
					maxL.next = cur;
				}
				maxL = cur;
			}
			cur = cur.next;
		}
		print(min);
		print(max);
		if(maxL != null) maxL.next = null;
		if(minL != null) {
			SENTINAL.next = min;
			minL.next = max;
		} else {
			SENTINAL.next = max;
		}
		return this;
	}
	
	private static void print(Node<Integer> node) {
		Node<Integer> cur = node;
		System.out.print("node=[");
		while (cur != null) {
			System.out.print(cur.value+";");
			cur = cur.next;
		}
		System.out.print("]");
		System.out.println();
	}
	
	public static void main(String[] args) {
		PartionLinkedList rList = new PartionLinkedList(-9);
//		rList.insert(3).insert(7)
//		.insert(2)
//		.insert(1)
//		.insert(3)
//		.insert(2)
//		.insert(6)
//		.insert(2)
//		.insert(3)
//		.insert(8);
		for(int i=0;i<10;i++) {
			int insert = RandomUtil.getInt(10);
			rList.insert(insert);
		}
		PrintUtil.print(rList);
		
		PrintUtil.print(rList.partion(5));
		
		PrintUtil.print(rList.partion(1));
		
		PrintUtil.print(rList.partion(8));
	}

}
