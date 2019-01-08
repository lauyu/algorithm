package com.alg.stack;

public class ReversePolishNotation {

	private Stack<Integer> stack = new LinkedListBasedStack<>();
	
	public int reversePolishMotation(String s) {
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if(isOperator(c)) {
				int right = stack.pop();
				int left = stack.pop();
				stack.push(operate(left, right, c));
			} else {
				stack.push(Integer.valueOf(String.valueOf(c)));
			}
			
		}
		return stack.pop();
	}
	
	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	private static int operate(int left, int right, char c) {
		if(c == '+') {
			return left + right;
		} else if (c == '-') {
			return left - right;
		} else if (c == '*') {
			return left * right;
		} else {
			return left / right;
		}
	}
	
//	private static String toReversePolishNotation(String s) {
//		
//	}
	
	public static void main(String[] args) {
		ReversePolishNotation calc = new ReversePolishNotation();
		System.err.println(calc.reversePolishMotation("563+*3/1-"));
	}

}
