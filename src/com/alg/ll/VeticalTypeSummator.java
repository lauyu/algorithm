package com.alg.ll;

import java.util.Random;

import com.alg.util.RandomUtil;

public class VeticalTypeSummator {

	public static void main(String[] args) {
		
		Node left = null;
		Node right = null;
		for(int i=0;i<5;i++) {
			if(left==null) left = new Node(RandomUtil.getInt(10));
			else {
				left = new Node(RandomUtil.getInt(10), left);
			}
//			System.out.println(RandomUtil.getInt(10));
			if(right==null) right = new Node(RandomUtil.getInt(10));
			else {
				right = new Node(RandomUtil.getInt(10), right);
			}
		}
		print(left);
		print(right);
		
		VeticalTypeSummator summator = new VeticalTypeSummator();
		Node result = summator.add(left, right);
//		print(result);
	}
	
	private Node add(Node node1, Node node2) {
		Node left = node1;
		Node right = node2;
		Node result = null;
		int carryFlag = 0;
		while (left!=null || right!=null) {
			int leftVal = left!=null? left.value:0;;
			int rightVal = right!=null? right.value:0;
			int value = (leftVal+rightVal + carryFlag )%10;
			carryFlag = (leftVal+rightVal + carryFlag )/10;
			System.out.print(value);
			if(result==null) {
				result = new Node(value);
			} else {
				result.next = new Node(value);
				result = result.next;
			}
			left = left!=null? left.next:null;
			right = right!=null? right.next:null;
		}
		if(carryFlag > 0)
			System.out.print(carryFlag);
		return result;
	}
	
	private static void print(Node node) {
		StringBuilder builder = new StringBuilder();
		if(builder.length()==0) {
			builder.append("linkedList[");
		}
		if(node == null) {
			builder.append("empty");
		} else {
			Node next = node;
			while (next != null) {
				builder.append("ele->").append(next.value).append(";");
				next = next.next;
			}	
		}
		builder.append("]");
		System.out.println(builder.toString());
	}
	
	static class Node {
		int value;
		Node next;
		
		public Node(int value) {
			this.value = value;
		}
		
		public Node(int value, Node node) {
			this.value = value;
			this.next = node;
		}
	}
	
}


