package com.alg.stack;

public class LinkedListBaseDeque<T> extends LinkedListBasedStack<T> implements Deque<T> {
	
	@Override
	public T peek() {
		if(isEmpty()) return null;
		return SENTINAL.getNext().getValue();
	}
	
	
}
