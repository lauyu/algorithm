package com.alg.pq;


public interface MaxPQ<K extends Comparable<K>> {
	void insert(K k);
	
	K max();
	
	K deleteMax();
	
	boolean isEmpty();
	
	int size();
	
}
