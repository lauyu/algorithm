package com.alg.ll;

import com.alg.ll.VeticalTypeSummator.Node;

public abstract class SingleLinkedListBase {
	
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
	
	protected static void print(Node node) {
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
}
