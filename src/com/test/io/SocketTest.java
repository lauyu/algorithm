package com.test.io;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {

	public SocketTest() {
	}

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 9000);
	}
}
