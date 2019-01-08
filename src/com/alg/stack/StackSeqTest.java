package com.alg.stack;

public class StackSeqTest {

	private Deque<Character> deque = new LinkedListBaseDeque<>();
	
	/**
	 * 待测试的序列，如果为出栈的一种可能，那么对于每一个元素，其匹配情况有：
	 * 1、和当前比较的原串相等；
	 * 2、如果栈不为空，等于栈顶的元素；如果不等于栈顶元素，入栈；
	 * 3、在剩余的原串中查找
	 * 
	 * @param seq
	 * @param test
	 * @return
	 */
	private boolean test(char[] seq, char[] test) {
		if(seq.length!=test.length)
			throw new IllegalArgumentException();
		int j=0;
		int i=0;
		while (i<test.length) {
			char t = test[i];
			if(j < seq.length) {
				if(seq[j] == t) {
					i++;
					j++;
				} else {
					if(deque.isEmpty() || deque.peek() != t) {
						System.out.println("push "+seq[j]);
						deque.push(seq[j]);
						j++;
					} else {
						deque.pop();
						i++;
					}
				}
			} else {
				if(deque.isEmpty() || deque.peek() != t)
					return false;
				else {
					deque.pop();
					i++;
				}
			}
		}
		System.out.println("i="+i+", j="+j+", deque.Empty="+deque.isEmpty());
		return true;
	}
	
	/**
	 * 按照课件介绍的思路
	 * @param seq
	 * @param test
	 * @return
	 */
	private boolean testByBook(char[] seq, char[] test) {
		if(seq.length!=test.length)
			throw new IllegalArgumentException();
		int i=0, j=0;
		while (i<test.length) {
			char t = test[i];
			if(deque.isEmpty() || deque.peek().charValue()!=t) {
				if(j >= seq.length)
					return false;
				deque.push(seq[j]);
				System.out.println("push "+seq[j]);
				j++;
				
			} else {
				System.out.println("t="+test[i]+", s="+deque.peek());
				deque.pop();
				i++;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		StackSeqTest seqTest = new StackSeqTest();
		String seq = "ABCDEFG";
		String test = "BAEDCFG";
		System.out.println(seqTest.test(seq.toCharArray(), test.toCharArray()));
		while (!seqTest.deque.isEmpty()) {
			seqTest.deque.pop();
		}
		seqTest = new StackSeqTest();
		System.out.println(seqTest.testByBook(seq.toCharArray(), test.toCharArray()));
	}

}
