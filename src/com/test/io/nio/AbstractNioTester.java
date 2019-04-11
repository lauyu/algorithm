package com.test.io.nio;

import java.nio.channels.SocketChannel;

import com.test.BaseTester;

public abstract class AbstractNioTester extends BaseTester {


	protected static void printSocketChannel(SocketChannel sc) {
		tPrint("isBlocking="+sc.isBlocking()+", isConnected="+sc.isConnected()+", isOpen="+sc.isOpen()+", isRegistered="+sc.isRegistered());
	}
	
}
