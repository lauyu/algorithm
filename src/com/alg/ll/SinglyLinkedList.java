package com.alg.ll;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
	protected final Node<T> SENTINAL = new Node<T>(null);
	
	protected int size;
	
	public SinglyLinkedList() {
//		SENTINAL.next = header;
	}
	
	public SinglyLinkedList<T> insert(T t) {
		Node<T> header = SENTINAL.next;
		SENTINAL.next = new Node<>(t, header);
		size++;
		afterInsert(SENTINAL.next);
		return this;
	}
	
	protected void afterInsert(Node<T> insert) {
		
	}
	
//	public boolean delete(T t) {
//		Integer
//	}
	
	protected static class Node<T> {
		T value;
		Node<T> next;
		
		public Node(T value) {
			this.value = value;
		}
		
		public Node(T value, Node<T> node) {
			this.value = value;
			this.next = node;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr(SENTINAL);
	}
	
	private class Itr implements Iterator<T> {
		private Node<T> preHeader;
		public Itr(Node<T> preHeader) {
			this.preHeader = preHeader;
		}
		@Override
		public boolean hasNext() {
			return preHeader.next!=null;
		}

		@Override
		public T next() {
			Node<T> next = preHeader.next;
			preHeader = next;
			return next.getValue();
		}
		
	} 
	
}
