package com.alg.string;

public class Permutation {

	public void perm(String s) {
		char[] charArray = s.toCharArray();
		doPerm(charArray, 0);
	}
	
	private String doPerm(char[] seq, int offset) {
//		System.out.println("seq[]="+seq[offset]+", offset="+offset);
		if(offset == seq.length-1) {
			System.out.println(new String(seq));
			return new String(seq);
		}
		int next=offset;
		String p = null;
		for(int i=offset;i<seq.length;i++) {
			swap(seq, offset, i);
			p = doPerm(seq, offset+1);
			swap(seq, i, offset);
//			next = offset;
		}
		return p;
	}
	
	private String doPerm2(char[] seq, int offset) {
//		System.out.println("seq[]="+seq[offset]+", offset="+offset);
		if(offset == seq.length-1) {
			System.out.println(new String(seq));
			return new String(seq);
		}
		int next=offset;
		String p = null;
		for(int i=offset;i<seq.length;i++) {
			if(offset != i && seq[offset] == seq[i])
				continue;
			swap(seq, offset, i);
			p = doPerm(seq, offset+1);
			swap(seq, i, offset);
//			next = offset;
		}
		return p;
	}
	
	private void doPerm3(char seq, int n) {
		
	}
	
	private void swap(char[] seq, int from, int to) {
		char s = seq[from];
		seq[from] = seq[to];
		seq[to] = s;
	}
	
	public static void main(String[] args) {
		Permutation permutation = new Permutation();
//		permutation.perm("1234");
		
		permutation.doPerm2("1213".toCharArray(), 0);
	}

}
