package com.alg.stack;

import java.util.LinkedList;
import java.util.List;

import com.alg.ll.SinglyLinkedList;
import com.alg.util.RandomUtil;

public class LinkedListBasedStack<T> extends SinglyLinkedList<T> implements Stack<T>{
	
	private Node<T> tail;
	
	public LinkedListBasedStack() {
	}

	@Override
	protected void afterInsert(Node<T> insert) {
		if(insert.getNext() == null) tail = insert;
	}
	
	@Override
	public void push(T t) {
		insert(t);
	}

	@Override
	public T pop() {
		alertNotEmpty();
		Node<T> header = SENTINAL.getNext();
		SENTINAL.setNext(header.getNext());
		size--;
		return header.getValue();
	}

	private void alertNotEmpty() {
		if(isEmpty())
			throw new IllegalStateException("stack is underflow");
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new LinkedListBasedStack<>();
		int i=10;
		for(int j=0;j<i;j++) {
			int k = RandomUtil.getInt(10);
			System.out.println("push-> "+k);
			stack.push(k);
		}
		System.out.println();
		for(int j=0;j<i;j++) {
			System.out.print("pop-> "+stack.pop()+" ");
		}
//		System.out.println(stack.pop());
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public T peek() {
		alertNotEmpty();
		return SENTINAL.getNext().getValue();
	}
}
