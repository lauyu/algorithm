package com.alg.stack;

public interface Stack<T> {

	void push(T t);
	
	T pop();
	
	/**
	 * 取出栈顶元素，不从栈中移除元素
	 * @return
	 */
	T peek();
	
	boolean isEmpty();
}
