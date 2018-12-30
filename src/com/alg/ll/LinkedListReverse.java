package com.alg.ll;

import com.alg.util.RandomUtil;

public class LinkedListReverse extends SingleLinkedListBase{

	public static void main(String[] args) {
		Node left = null;
		for(int i=0;i<10;i++) {
			if(left==null) left = new Node(RandomUtil.getInt(10));
			else {
				left = new Node(RandomUtil.getInt(10), left);
			}
		}
		print(left);
		LinkedListReverse reverse = new LinkedListReverse();
		Node header = left;
		reverse.reverse(left, 1, 2);
		print(header);
	}

	private Node reverse(Node node, int m, int n) {
		Node header = node;
		Node mNode = null;
		for(int i=0; i<m-1; i++) {
			header = header.next;
		}
		mNode = header.next;
		Node cur = header.next.next;
		Node next = null;
		for(int i=m; i<n; i++) {
			next = cur.next;
			mNode.next = cur.next;
			cur.next = header.next;
			header.next = cur;
			cur = next;
		}
		return node;
	}
	
}
