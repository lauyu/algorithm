package com.test.jvm;

import com.test.BaseTester;
import org.junit.Assert;
import org.junit.Test;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GCTest extends BaseTester {

	private static final int KB = 1024;
	private static final int MB = KB * 1024;
	static byte[] arr;
	public static void main(String[] args) {
//		 byte[] arr1 = new byte[1*MB];
//		 System.out.println("after 2MB byte[]: "+arr1);
//		 tSleep(1000);

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
//
//		byte[] arr2 = new byte[512*KB];
//		 System.out.println(sdf.format(new Date())+" after 0.2*MB int[]: "+arr2);
//		 tSleep(1000);
//
//		byte[] arr3 = new byte[512*KB];
//		System.out.println(sdf.format(new Date())+"after 1*MB int[]: "+arr2);
//		tSleep(1000);
//
//		byte[] arr4 = new byte[512*KB];
//		System.out.println(sdf.format(new Date())+"after 1*MB int[]: "+arr2);
//		tSleep(1000);
//
//		byte[] arr5 = new byte[512*KB];
//		System.out.println(sdf.format(new Date())+"after 1*MB int[]: "+arr2);
//		tSleep(1000);
//
//		byte[] arr6 = new byte[1*MB];
//		 System.out.println(sdf.format(new Date())+"after 1*MB int[]");
//		 tSleep(1000);

		// byte[] arr4 = new byte[100*KB];
		// System.out.println("after 100*k byte[]");
		// tSleep(1000);

		// byte[] arr5 = new byte[10*KB];
		// System.out.println("after 10*k byte[]");
		// tSleep(1000);

		// byte[] arr6 = new byte[800*KB];
		// System.out.println("after 800*k byte[]");
		// tSleep(1000);

		// byte[] arr7 = new byte[10*MB];
		// System.out.println("after 5MB byte[]");

		// OOMTest test = new OOMTest();
		// test.nestMethod(new ArrayList<>());
		testPerNew();
	}

	/**
	 * 测试过早晋升老年代，原理：
	 */
	private static void testPerNew() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		List<byte[]> young = new ArrayList<>();
		int i = 0;
		int yc = 0;

		while (true) {
			//创建Scanner对象
			//System.in表示标准化输出，也就是键盘输出
			Scanner sc = new Scanner(System.in);
			int cmd = sc.nextInt();
			i = (cmd<1? 1:cmd);
			int size = i * 4 * MB - 16;
			arr = new byte[size];
			if(cmd == 0) {
				young.add(arr);
				System.out.println("   >>>过早晋升收集，zise="+young.size());
			}
		}

	}

}
