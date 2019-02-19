package com.alg.util;

import java.util.Random;

public class RandomUtil {
	static Random random = new Random(System.currentTimeMillis());
	
	public static int getPosInt() {
		return Math.abs(random.nextInt());
	}
	
	public static int getInt(int noMoreThan) {
		return random.nextInt(noMoreThan);
	}
	
	public static int getInt() {
		return random.nextInt();
	}
}

