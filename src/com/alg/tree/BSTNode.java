package com.alg.tree;

public class BSTNode<K extends Comparable<K>, V> {

	private K key;
	private V value;
	
//	TODO 二叉查找树的结点没有父结点的引用？
//	BSTNode<K, V> parent;
	BSTNode<K, V> left;
	BSTNode<K, V> right;
	
	public BSTNode(K k, V v) {
		this.key = k;
		this.value= v;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public BSTNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<K, V> left) {
		this.left = left;
	}

	public BSTNode<K, V> getRight() {
		return right;
	}

	public void setRight(BSTNode<K, V> right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "node[k="+key+",v="+value+"]";
	}
}
