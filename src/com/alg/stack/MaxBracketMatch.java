package com.alg.stack;

public class MaxBracketMatch extends BracketSequenceMatcher {
	private Stack<Integer> stack = new LinkedListBasedStack<>();
	public MaxBracketMatch() {
		// TODO Auto-generated constructor stub
	}

	public int maxMatch(String seq) {
		int start = 0;
		int max = 0;
		for(int i=start;i<seq.length();i++) {
			char cur = seq.charAt(i);
			if(isLeft(cur)) {
				stack.push(i);
			}else {
				if(stack.isEmpty()) {
					start = i+1;
				} else {
					max = max(max, i-stack.pop()+1+start);
				}
			}
		}
		return max;
	}
	
	private static int max(int i1, int i2) {
		return i1>i2? i1:i2;
	}
	
	public static void main(String[] args) {
		MaxBracketMatch  match = new MaxBracketMatch();
		System.out.println(match.maxMatch("()()"));
	}

}
