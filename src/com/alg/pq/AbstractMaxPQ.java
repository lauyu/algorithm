package com.alg.pq;

public abstract class AbstractMaxPQ
	implements MaxPQ<Double> {

	private int size;
	
	protected void setSize(int size) {
		this.size = size;
	}
	
	public void insert(Double k) {
		doInsert(k);
		size++;
	}
	
	protected boolean more(Double k1, Double k2) {
		return k1.compareTo(k2) > 0;
	}
	
	@Override
	public Double max() {
		if(isEmpty()) throw new IllegalStateException("pq is empty");
		return doMax();
	}
	
	protected abstract Double doMax();
	
	protected abstract void doInsert(Double k);
	
	public Double deleteMax() {
		if(isEmpty()) throw new IllegalStateException("pq is empty");
		Double k = doDeleteMax();
		size--;
		return k;
	}
	
	protected abstract Double doDeleteMax();
	
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
}
