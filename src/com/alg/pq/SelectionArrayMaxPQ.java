package com.alg.pq;

public class SelectionArrayMaxPQ extends AbstractMaxPQ {

	private Double[] array;
	private int N=0;
	private int bound=0;
	public SelectionArrayMaxPQ(Double[] array) {
		this.array = array;
		if(this.array !=null) {
			setSize(this.array.length);
			N=this.array.length;
		}
	}
	
	@Override
	public Double doMax() {
		int i=bound;
		for(int j=bound;j<array.length;j++) {
			if(more(array[j], array[i]))
				i=j;
		}
		return array[i];
	}

	@Override
	protected void doInsert(Double k) {
		if(size()==array.length) {
			resize(2*size());
		}
		array[N++] = k;
	}
	
	private void resize(int newSize) {
		Double[] newArr = new Double[newSize];
		for(int i=0;i<array.length;i++)
			newArr[i] = array[i];
		array = newArr;
	}
	
	@Override
	protected Double doDeleteMax() {
		// TODO Auto-generated method stub
		return null;
	}

}
