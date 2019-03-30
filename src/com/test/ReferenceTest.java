package com.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class ReferenceTest {

	public static void main(String[] args) {
		ReferenceQueue queue = new ReferenceQueue ();
		PhantomReference pr = new PhantomReference (object, queue); 
	}

}
