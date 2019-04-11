package com.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DynamicProxyTest {

	public static void main(String[] args) {
		Hello list = new Hello() {
			
			@Override
			public String say(String name) {
				return "Hello "+name;
			}
		};
		MyHandler handler = new MyHandler(list);
		Hello f = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(),
                new Class<?>[] { Hello.class },
                handler);
		
//		list.say("lauyu");
		
		f.say("lauyu");
	}

	static interface Hello {
		String say(String name);
	}
	
	
	static class MyHandler implements InvocationHandler {
		Hello proxied;
		public MyHandler(Hello proxied) {
			this.proxied = proxied;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("before invoke....");
			return method.invoke(proxied, args);
		}
		
	}
}
