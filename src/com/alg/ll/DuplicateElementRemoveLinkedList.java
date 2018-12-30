package com.alg.ll;

import com.alg.util.PrintUtil;
import com.alg.util.RandomUtil;

public class DuplicateElementRemoveLinkedList extends SinglyLinkedList<Integer>{

	public DuplicateElementRemoveLinkedList removeDuplicate() {
		Node<Integer> first = SENTINAL.next;
		if(first == null) return this;
		while (first.next != null) {
			if(first.next.value == first.value) {
				Node<Integer> dup = first.next;
				first.next = dup.next;
				dup.next = null;
			} else {
				first = first.next;	
			}
		}
		return this;
	}
	
	public static void main(String[] args) {
		DuplicateElementRemoveLinkedList rList = new DuplicateElementRemoveLinkedList();
		for(int i=0;i<0;i++) {
			int insert = RandomUtil.getInt(10);
			rList.insert(insert);
		}
		PrintUtil.print(rList);
		PrintUtil.print(rList.removeDuplicate());
	}

}
