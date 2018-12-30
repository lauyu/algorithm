package com.alg.stack;

public class BracketSequenceMatcher {
	private Stack<Character> stack = new LinkedListBasedStack<>();
	
	public BracketSequenceMatcher() {
	}

	public boolean match(String seq) {
		for(int i=0; i<seq.length(); i++) {
			char cur = seq.charAt(i);
			System.err.println("matching "+cur);
			if(isLeft(cur)) {
				stack.push(cur);
			} else if (isRight(cur)) {
				if(stack.isEmpty()) return false;
				else if (!isMatch(stack.pop().charValue(), cur)) {
					return false;
				}
			} else {
				throw new IllegalArgumentException("invalid input");
			}
		}
		return stack.isEmpty();
	}
	
	
	
	private void resetStack() {
		while (!stack.isEmpty()) {
			stack.pop();
		}
	}
	
	protected static boolean isLeft(char c) {
		return c=='{' || c=='[' | c =='(';
	}
	
	protected static boolean isRight(char c) {
		return c=='}' || c==']' | c ==')';
	}
	
	protected static boolean isMatch(char l, char r) {
		return r==')'? l=='(':
					r==']'? l=='[':
						l=='{';
	}
	
	public static void main(String[] args) {
//		System.out.println('s' == "s".charAt(1));
		BracketSequenceMatcher matcher = new BracketSequenceMatcher();
		System.out.println(matcher.match("(()[({})])"));
	}

}
