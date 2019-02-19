package com.alg.recursion;

public class Hannoi {

	/**
	 * n个盘的移动（初始柱以A表示），可以分解为把n-1个盘移动到辅助柱（以B表示），把最大的盘移动到目标柱（以C表示），再把n-1个盘从B移动经A移到到C。
	 * 递归n到1，即可完成
	 */
	public void move(char from, char aux, char to, int n) {
		if(n == 1) {
			moveone(from, to, n);
			return;
		}
		move(from, to, aux, n-1);
		moveone(from, to, n);
		move(aux, from, to, n-1);
	}
	
	private void moveone(char from, char to, int n) {
		System.out.println("move " + n + " from " + from + " to " + to);
	}
	
//	public int calcStep(char[] status, int n, char from, char aux, char to) {
//		if(n == 1) return 0;
//		if(status[n-1] == aux) return -1;
//		if(status[n-1] == from) {
//			int c = 
//		}
//	}
	
	public static void main(String[] args) {
		new Hannoi().move('A', 'B', 'C', 5);
	}

}
