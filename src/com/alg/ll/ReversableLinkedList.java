package com.alg.ll;

import com.alg.util.PrintUtil;
import com.alg.util.RandomUtil;

public class ReversableLinkedList extends SinglyLinkedList<Integer> {

	public ReversableLinkedList reverse(int m, int n) {
		if(n<=m || m<1 || n>size) throw new IllegalArgumentException("invalid bound");
		Node<Integer> header = SENTINAL;
		for(int i=0; i<m-1; i++) {
			header = header.next;
		}
		Node<Integer> mNode = header.next;
		Node<Integer> cur = mNode.next;
		Node<Integer> next = null;
		for(int i=m; i<n; i++) {
			next = cur.next;
			mNode.next = next;
			cur.next = header.next;
			header.next = cur;
			cur = next;
		}
		return this;
	}
	
	/**
	 * 链表整体反转
	 */
	public void totalReverse() {
		Node<Integer> pre = SENTINAL.next;
		if(pre == null) return;
		Node<Integer> cur = pre.next;
		pre.next = null;
		while (cur != null) {
			Node<Integer> next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		SENTINAL.next = pre;
	}
	
	public static void main(String[] args) {
		ReversableLinkedList rList = new ReversableLinkedList();
		for(int i=0;i<10;i++) {
			int insert = RandomUtil.getInt(10);
			System.out.println("insert to list: "+insert);
			rList.insert(insert);
		}
//		System.out.println(rList.size);
		PrintUtil.print(rList);
//		PrintUtil.print(rList.reverse(1, 2));
//		PrintUtil.print(rList.reverse(1, rList.size));
//		PrintUtil.print(rList.reverse(4, 6));
		rList.totalReverse();
		PrintUtil.print(rList);
	}

}
