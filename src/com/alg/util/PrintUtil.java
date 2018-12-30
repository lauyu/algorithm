package com.alg.util;

import java.util.Iterator;

public class PrintUtil {

	public static <T> void print(Iterable<T> ite) {
		Iterator<T> iterator = ite.iterator();
		StringBuilder builder = new StringBuilder();
		if(builder.length()==0) {
			builder.append("linkedList[");
		}
		if(!iterator.hasNext()) {
			builder.append("empty");
		} else {
			while (iterator.hasNext()) {
				builder.append("e->").append(iterator.next()).append(";");
			}	
		}
		builder.append("]");
		System.out.println(builder.toString());
	}
	
}
